// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var data = document.getElementById("pieData").value.split("|");
var stringData = data[1].split(" ");

var stringLabels = data[0].split(", ");
var labelsData = [];
for(let i = 0; i < stringLabels.length; i++) {
	labelsData.push(stringLabels[i]);
}
labelsData.push("Còn lại");
var pieData = [];
var total = 100;
for(let i = 0; i < stringData.length; i++) {
	let num = parseFloat(stringData[i]);
	pieData.push(num);
	total = total - num;
}
pieData.push(total);

var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: labelsData,
    datasets: [{
      data: pieData,
      backgroundColor: ['#e74a3b', '#f6c23e', '#4e73df', '#1cc88a'],
      hoverBackgroundColor: [ '#e01602', '#f5b920', '#2e59d9', '#17a673'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});


var ctx2 = document.getElementById("myPieChart2");
var data2 = document.getElementById("pieData2").value.split("|");
var stringData2 = data2[1].split(" ");
var stringLabels2 = data2[0].split(", ");
var labelsData2 = [];
for(let i = 0; i < stringLabels2.length; i++) {
	labelsData2.push(stringLabels2[i]);
}
labelsData2.push("Còn lại");
var pieData2 = [];
var total2 = 100;
for(let i = 0; i < stringData2.length; i++) {
	let num = parseFloat(stringData2[i]);
	pieData2.push(num);
	total2 = total2 - num;
}
pieData2.push(total2);
var myPieChart2 = new Chart(ctx2, {
  type: 'doughnut',
  data: {
    labels: labelsData2,
    datasets: [{
      data: pieData2,
      backgroundColor: ['#e74a3b', '#f6c23e', '#4e73df', '#1cc88a'],
      hoverBackgroundColor: [ '#e01602', '#f5b920', '#2e59d9', '#17a673'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
