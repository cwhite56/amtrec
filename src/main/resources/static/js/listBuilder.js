function countUp(spell) {

                if(checkArchtype(spell)) return;

                const inputField = document.getElementById(spell);


                if(parseInt(inputField.value) + 1 > parseInt(inputField.dataset.limit)) return;

                const levelPoints = document.querySelectorAll('[id^="level"]');

                let cost = inputField.dataset.cost;

                const spellLevel = inputField.dataset.level;


                for(let i = 0; i < 6; i++) {

                    if(i + 1 < spellLevel) continue;

                    if(levelPoints[i].value == 0) continue;

                    const startingVal = levelPoints[i].value;

                    while(levelPoints[i].value > 0  && cost > 0) {

                        levelPoints[i].value--;
                        cost--;
                    }

                    if(cost == 0) break;

                    // Not enough points remaining
                    else if(cost > 0 && i == 5) {

                        levelPoints[i].value = startingVal;

                        return;
                    }
                }

                if(cost > 0) return;

                inputField.value++;
            }

            function countDown(spell) {

                const inputField = document.getElementById(spell);

                let val = parseInt(inputField.value);

                if(val <= 0) {
                    return;
                }

                const levelPoints = document.querySelectorAll('[id^="level"]');

                let cost = inputField.dataset.cost;

                for (let i = 5; i >= 0; i--) {

                    if(levelPoints[i].value >= 5) continue;

                    while(levelPoints[i].value < 5 && cost > 0) {
                        levelPoints[i].value++;
                        cost--;
                        
                        //Level 6 gets an extra point for LTP bonus
                        if(i == 5 && cost > 0) {
                            levelPoints[i].value++;
                            cost--;
                        }
                    }

                    if(cost == 0) break;

                }

                inputField.value--;
            }

            function setLTP(checkboxId) {

                const checkbox = document.getElementById(checkboxId);

                if(freshList()) {

                    const level6 = document.getElementById('level6Points');
                    
                    if(checkbox.checked) level6.value++;

                    else level6.value--;
                }

                else {
                    checkbox.checked = true;
                }
            }

            function freshList() {

                const levelPoints = document.querySelectorAll('[id^="level"]');

                for(let i = 0; i < 6; i++) {
                    if(levelPoints[i].value != 5 && levelPoints[i].value != 6) return false;
                }

                return true;
            }

            function deleteList() {

                const titleField = document.getElementById('title');
                const title = titleField.value;

                const userField = document.getElementById('user');
                const user = userField.value;

                fetch(`/api/v1/users/${user}/spell-lists/${title}`,{
                    method: 'DELETE'
                }).then(res => {

                    if(!res.ok) {
                        throw new Error("Error: could not delete spell list");
                    }
                    return res.json;

                }).then(data => {
                    window.location.href = `/users/${user}/spell-lists/dashboard`;
                });

            }

            function exSpell(exNum, isEx) {
                
                let spell;
                let sel;

                if(exNum == 1) {
                    sel = document.getElementById('exp1');
                    spell = sel.value;
                }
                else if (exNum == 2){
                    sel = document.getElementById('exp2');
                    spell = sel.value;
                }
                
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
                        if(spellInput.dataset.school != "flame" || spellInput.dataset.school != "death") return true;
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
                    window.location.href = `/users/${username}/spell-lists/dashboard`;
                });
            });


                const sel1 = document.getElementById('exp1');

                if(sel1.value != '') sel1.disabled = true;
            
                const sel2 = document.getElementById('exp2');

                if(sel2.value != '') sel2.disabled = true;
