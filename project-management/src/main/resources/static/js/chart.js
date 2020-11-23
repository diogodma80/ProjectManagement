// source: chartjs.org

// For a pie chart
new Chart(document.getElementById("pieChart"), {
    type: 'pie',
	data: {
        labels: ['January', 'February', 'March'],
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'rgb(255, 99, 132)',
            data: [20, 30, 45]
        }]
    },

    // Configuration options go here
    options: {}
});