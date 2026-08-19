function submitStatRequest() {
            const form = document.querySelector('.form');


            const formData = new FormData(form);

            const data = Object.fromEntries(formData);

            fetch('/api/v1/spell-lists/global-stats', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)

            }).then(res => {
                    
                if(!res.ok) {
                    throw new Error('Could not register user');
                }
                return res.json();
                    
            }).then(data => {

                const inclusionArray = data.inclusion;
                const averageArray = data.average;

                const inclusionSpans = document.querySelectorAll('.inclusion-cell');
                const averageSpans = document.querySelectorAll('.average-cell');

                let i = 0;
                for(const span of inclusionSpans) {
                    let val = Number(inclusionArray[i]);

                    if(Number.isNaN(val))span.textContent = "0.00%";
                    else span.textContent = val.toFixed(2) + "%";
                    i++;
                }

                i = 0;
                for(const span of averageSpans) {
                    let val = Number(averageArray[i]);

                    if(Number.isNaN(val)) span.textContent = "0.00";
                    else span.textContent = val.toFixed(2);
                    i++;
                }
                
            });
        }