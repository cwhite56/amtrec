            function countUp(spell) {

                if(checkArchtype(spell)) return -1;

                const inputField = document.getElementById(spell);

                if(parseInt(inputField.value) + 1 > parseInt(inputField.dataset.limit)) return -1;

                if(spendPoints(inputField) < 0) return -1;

                let endVal = parseInt(inputField.dataset.valueMultiplier) + parseInt(inputField.value);
                inputField.value = endVal;
            }

            function countDown(spell) {

                const inputField = document.getElementById(spell);

                let val = parseInt(inputField.value);

                if(val <= 0) {
                    return -1;
                }

                refundPoints(inputField);

                let endVal = parseInt(inputField.value) - parseInt(inputField.dataset.valueMultiplier);
                inputField.value = endVal;

                if(isEmptyList()) enableLTP();
            }

            function spendPoints(spell) {
                const levelPoints = document.querySelectorAll('[id^="level"]');

                let cost = parseInt(spell.dataset.cost) * parseInt(spell.dataset.costMultiplier);


                const spellLevel = parseInt(spell.dataset.level);


                for(let i = 0; i < 6; i++) {

                    if(i + 1 < spellLevel) continue;

                    //if(levelPoints[i].value == 0) continue;

                    const startingVal = levelPoints[i].value;

                    while(levelPoints[i].value > 0  && cost > 0) {

                        levelPoints[i].value--;
                        cost--;
                    }

                    if(cost == 0) break;

                    // Not enough points remaining
                    else if(cost > 0 && i == 5) {

                        levelPoints[i].value = startingVal;

                        return -1;
                    }
                }

                if(cost > 0) return -1;

                return 0;
            }

            function refundPoints(spell) {
                const levelPoints = document.querySelectorAll('[id^="level"]');

                let cost = parseInt(spell.dataset.cost) * parseInt(spell.dataset.costMultiplier);


                for (let i = 5; i >= 0; i--) {

                    let remainingPoints = parseInt(pointsRemainingByLevel(i + 1, spell));

                    remainingPoints -= levelPoints[i].value;

                    while(remainingPoints > 0 && cost > 0) {
                        levelPoints[i].value++;
                        cost--;
                        remainingPoints--;

                    }

                    if(cost == 0) break;
                }
            }

            function pointsRemainingByLevel(rank, spell) {

                const spellsAtLevel = document.querySelectorAll(`[data-level="${rank}"]`);

                let count = 0;
                let max = 5;

                if(rank == 6 && isLTP()) max = 6;

                for(const spells of spellsAtLevel) {

                    let quantityPurchased = spells.value / parseInt(spells.dataset.valueMultiplier);

                    if(spell.id == spells.id) quantityPurchased--;

                    count += quantityPurchased * ( parseInt(spells.dataset.cost) * parseInt(spells.dataset.costMultiplier) );

                }
                return max - count;
            }

            function setLTP(checkboxId) {

                const checkbox = document.getElementById(checkboxId);
                const level6 = document.getElementById('level6Points');
                    
                if(checkbox.checked) {
                    level6.value++;
                    checkbox.disabled = true;
                }
                else level6.value--;
            }

            function isLTP() {
                const checkbox = document.getElementById('LTP');
                return checkbox.checked;
            }

            function enableLTP() {
                const checkbox = document.getElementById('LTP');
                checkbox.disabled = false;
            }

            function isEmptyList() {

                const levelPoints = document.querySelectorAll('[id^="level"]');

                for(let i = 0; i < 5; i++) {
                    if(levelPoints[i].value != 5) return false;
                }

                if(levelPoints[5].value != 6) return false;

                return true;
            }

            function deleteList() {

                const titleField = document.getElementById('title');
                const title = titleField.value;

                const userField = document.getElementById('user');
                const user = userField.value;
                console.log(`user="${user}" title="${title}"`);

                fetch(`/api/v1/users/${encodeURIComponent(user)}/spell-lists/${encodeURIComponent(title)}`,{
                    method: 'DELETE'
                }).then(res => {

                    if(!res.ok) {
                        throw new Error("Error: could not delete spell list");
                    }
                    window.location.href = `/users/${user}/spell-lists/dashboard`;
                });
            }

            function exSpell(exNum, isEx) {
                
                let spell;
                let sel;

                if(exNum == 1) sel = document.getElementById('exp1');
                
                else if (exNum == 2) sel = document.getElementById('exp2');
                
                spell = sel.value;
                
                const spellLabel = document.querySelector(`label[for="${spell}"]`);

                if(isEx) {

                spellLabel.textContent += ' (ex)';  
                sel.disabled = true;

                }
                else {
                    spellLabel.textContent = spellLabel.textContent.replace(/\s*\(ex\)/i, '');

                    sel.disabled = false;
                    sel.selectedIndex = 0;
                }
            }

            function checkArchtype(spell) {
                const getArchtype = document.getElementById('archtype');
                const spellInput = document.getElementById(spell);

                switch(getArchtype.value) {

                    case 'battlemage':
                        if(spellInput.dataset.type == "ball" || spellInput.dataset.type == "enchantment") return true;
                        break;
                    
                    case 'evoker':
                        if(spellInput.dataset.range == "20" || spellInput.dataset.range == "50") return true;
                        break;

                    case 'warlock':
                        if(spellInput.dataset.type == "verbal" || spellInput.dataset.type == "ball") {

                            if(spellInput.dataset.school != "flame" && spellInput.dataset.school != "death") return true;
                        }
                        break;

                    case 'legend':
                        if(spellInput.id == "swift")return true;
                        break;

                    case 'summoner':
                        if(spellInput.dataset.type == "verbal") {
                            if(spellInput.dataset.range != "touch" && spellInput.dataset.range != "self") return true;
                        }
                        if(spellInput.dataset.type == "weapon" && parseInt(spellInput.dataset.level) > 2) return true;
                        break;

                    case 'necromancer':
                        if(spellInput.dataset.school == "protection")return true;
                        break;

                    case 'warder':
                        if(spellInput.dataset.school == "death" || spellInput.dataset.school == "command" || spellInput.dataset.school == "subdual")return true;
                        break;

                    default:
                        return false;
                }

            }

            function setArchtype(spell) {
                const getArchtype = document.getElementById('archtype');
                getArchtype.value = spell; 
            }

            
        
            const form = document.querySelector('.form');

            form.addEventListener('submit', event => {
                event.preventDefault();

                const data = Object.fromEntries(new FormData(form));

                const spellInputs = document.querySelectorAll('.spell-input');
                data.spentPoints = Array.from(spellInputs).map(input => parseInt(input.value) || 0);

                const pointsPerLevel = document.querySelectorAll('.level-input');
                data.pointsRemainingByLevel = Array.from(pointsPerLevel).map(input => parseInt(input.value));

                const exp1Select = document.getElementById('exp1');
                data.exp1 = exp1Select.value;

                const exp2Select = document.getElementById('exp2');
                data.exp2 = exp2Select.value;
                
                const username = data.user;

                const isLTP = document.getElementById('LTP');
                data.LTP = isLTP.checked;


                fetch(`/api/v1/users/${username}/spell-lists`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)

                }).then(res =>  {

                    if(!res.ok) {

                        throw new Error('Error: could not save spell list');
                    }
                    return res.json();

                }).then(data => {
                    window.location.href = `/users/${username}/spell-lists/spellbook`;
                });
            });

            document.addEventListener('DOMContentLoaded', function () {
                document.querySelectorAll('.spell-row').forEach(function (row) {
                    var input = row.querySelector('.spell-input');
                    if (!input) return;

                    var costCell = row.querySelector('.cost-cell');
                    var limitCell = row.querySelector('.limit-cell');

                    if (costCell) {
                        costCell.textContent = input.dataset.cost || '\u2014';
                    }
                    if (limitCell) {
                        limitCell.textContent = input.dataset.limit || '\u2014';
                    }
                });
            });

            const sel1 = document.getElementById('exp1');

            if(sel1.value != '') sel1.disabled = true;
            
            const sel2 = document.getElementById('exp2');

            if(sel2.value != '') sel2.disabled = true;
