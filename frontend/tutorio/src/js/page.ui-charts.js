(function(){
  'use strict';

  Charts.init()
  
  var Performance = function(id, type = 'line', options = {}) {
    options = Chart.helpers.merge({
      scales: {
        yAxes: [{
          ticks: {
            callback: function(a) {
              if (!(a % 10))
                return "$" + a + "k"
            }
          }
        }]
      },
      tooltips: {
        callbacks: {
          label: function(a, e) {
            var t = e.datasets[a.datasetIndex].label || "",
                o = a.yLabel,
                r = "";
            return 1 < e.datasets.length && (r += '<span class="popover-body-label mr-auto">' + t + "</span>"), r += '<span class="popover-body-value">$' + o + "k</span>"
          }
        }
      }
    }, options)

    var data = {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [{
        label: "Performance",
        data: [0, 10, 5, 15, 10, 20, 15, 25, 20, 30, 25, 40]
      }]
    }

    Charts.create(id, type, options, data)
  }

  var Orders = function(id, type = 'roundedBar', options = {}) {
    options = Chart.helpers.merge({
      barRoundness: 1.2,
      scales: {
        yAxes: [{
          ticks: {
            callback: function(a) {
              if (!(a % 10))
                return "$" + a + "k"
            }
          }
        }]
      },
      tooltips: {
        callbacks: {
          label: function(a, e) {
            var t = e.datasets[a.datasetIndex].label || "",
                o = a.yLabel,
                r = "";
            return 1 < e.datasets.length && (r += '<span class="popover-body-label mr-auto">' + t + "</span>"), r += '<span class="popover-body-value">$' + o + "k</span>"
          }
        }
      }
    }, options)

    var data = {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [{
        label: "Sales",
        data: [25, 20, 30, 22, 17, 10, 18, 26, 28, 26, 20, 32]
      }]
    }

    Charts.create(id, type, options, data)
  }

  var Devices = function(id, type = 'doughnut', options = {}) {
    options = Chart.helpers.merge({
      tooltips: {
        callbacks: {
          title: function(a, e) {
            return e.labels[a[0].index]
          },
          label: function(a, e) {
            var t = "";
            return t += '<span class="popover-body-value">' + e.datasets[0].data[a.index] + "%</span>"
          }
        }
      }
    }, options)

    var data = {
      labels: ["Desktop", "Tablet", "Mobile"],
      datasets: [{
        data: [60, 25, 15],
        backgroundColor: [settings.colors.primary[500], settings.colors.accent[300], settings.colors.accent[100]],
        hoverBorderColor: "dark" == settings.charts.colorScheme ? settings.colors.gray[800] : settings.colors.white
      }]
    }

    Charts.create(id, type, options, data)
  }

  var TopicIQChart = function(id, type = 'radar', options = {}) {
    options = Chart.helpers.merge({
      elements: {
        point: {
          pointStyle: 'circle',
          radius: 4,
          hoverRadius: 5,
          backgroundColor: settings.colors.white,
          borderColor: settings.colors.primary[500],
          borderWidth: 2
        }
      },
      scale: {
        ticks: {
          display: false,
          beginAtZero: true,
          maxTicksLimit: 4
        },
        gridLines: {
          color: "dark" == settings.charts.colorScheme ? settings.colors.gray[900] : settings.colors.gray[300]
        },
        angleLines: {
          color: "dark" == settings.charts.colorScheme ? settings.colors.gray[900] : settings.colors.gray[300]
        },
        pointLabels: {
          fontSize: 14
        }
      },
      tooltips: {
        callbacks: {
          label: function(a, e) {
            var t = e.datasets[a.datasetIndex].label || "",
                o = a.yLabel,
                r = "";
            return 1 < e.datasets.length && (r += '<span class="popover-body-label mr-auto">' + t + "</span>"), r += '<span class="popover-body-value">' + o + " points</span>"
          }
        }
      }
    }, options)

    var data = {
      labels: ["JavaScript", "HTML", "Flinto", "Vue.js", "Sketch", "Priciple", "CSS", "Angular"],
      datasets: [{
        label: "Experience IQ",
        data: [30, 35, 33, 32, 31, 30, 28, 36],
        pointHoverBorderColor: settings.colors.accent[400],
        pointHoverBackgroundColor: settings.colors.white,
        borderJoinStyle: 'bevel',
        lineTension: .1
      }]
    }

    Charts.create(id, type, options, data)
  }

  ///////////////////
  // Create Charts //
  ///////////////////

  Performance('#performanceChart')
  
  Performance('#performanceAreaChart', 'line', {
    elements: {
      line: {
        fill: 'start',
        backgroundColor: settings.charts.colors.area
      }
    }
  })

  Orders('#ordersChart')

  Orders('#ordersChartSwitch')

  Devices('#devicesChart')

  TopicIQChart('#topicIqChart')

  $('[data-toggle="chart"]:checked').each(function (index, el) {
    Charts.add($(el))
  })

})()