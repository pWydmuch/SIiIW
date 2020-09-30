window.addEventListener('load', setup);

async function setup() {
    const ctx = document.getElementById('myChart').getContext('2d');
    const myData = await getData();
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: myData.generations,
            datasets: [
                {
                    label: 'best',
                    data: myData.bests,
                    fill: false,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                } ,
                {
                    label: 'avarage',
                    data: myData.avarages,
                    fill: false,
                    borderColor: 'rgba(12, 155, 132, 1)',
                    backgroundColor: 'rgba(12, 155, 132, 1)',
                    borderWidth: 1
                }  ,
                {
                    label: 'worsts',
                    data: myData.worsts,
                    fill: false,
                    borderColor: 'rgba(155, 23, 14, 1)',
                    backgroundColor: 'rgba(155, 23, 14, 1)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            elements: {
                point: {
                    radius: 0
                }
            }
        }
    });
}

async function getData() {
    // const response = await fetch('testdata.csv');
    const response = await fetch('../log.csv');
    const data = await response.text();
    console.log(data);
    const generations = [];
    const bests = [];
    const avarages = [];
    const worsts = [];
    const rows = data.split('\n').slice(1);
    rows.forEach(row => {
        const cols = row.split(',');
    generations.push(cols[0]);
    bests.push(cols[1]);
    avarages.push(cols[2]);
    worsts.push(cols[3]);}
);
    return {generations, bests, avarages, worsts};
}