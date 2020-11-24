// source: chartjs.org
var chartDataString = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataString);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("pieChart"), {
    type: 'pie',
	data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title: {
    		display: true,
    		text: 'Project Statuses'
    	}
    }
});

// "[{"value": 1, "label": "COMPLETED"}, {"value": 1, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html) {
	var txt = document.createElement("textArea");
	txt.innerHTML = html;
	return txt.value;
}