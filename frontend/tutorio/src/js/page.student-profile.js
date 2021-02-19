(function(){
  'use strict';

  Charts.init()

  var earnings = []

  // Create a date range for the last 7 days
  var start = moment().subtract(6, 'days').format('YYYY-MM-DD') // 7 days ago
  var end = moment().format('YYYY-MM-DD') // today
  var range = moment.range(start, end)

  // Create the earnings graph data
  // Iterate the date range and assign a random ($) earnings value for each day
  range.by('days', function(moment) {
    earnings.push({
      y: Math.floor(Math.random() * 200) + 15,
      x: moment.toDate()
    })
  })

  var WeekIQChart = function(id, type = 'line', options = {}) {
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
      scales: {
        yAxes: [{
          gridLines: {
            display: false
          },
          ticks: {
            display: false
          }
        }],
        xAxes: [{
          gridLines: {
            display: false
          },
          type: 'time',
          distribution: 'series',
          time: {
            unit: 'day',
            stepSize: 1,
            autoSkip: false,
            displayFormats: {
              day: 'ddd'
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
            return 1 < e.datasets.length && (r += '<span class="popover-body-label mr-auto">' + t + "</span>"), r += '<span class="popover-body-value">' + o + " points</span>"
          }
        }
      }
    }, options)

    var data = {
      datasets: [{
        label: "Experience IQ",
        data: earnings,
        pointHoverBorderColor: settings.colors.success[400],
        pointHoverBackgroundColor: settings.colors.white
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
          color: settings.colors.gray[300]
        },
        angleLines: {
          color: settings.colors.gray[300]
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
        pointHoverBorderColor: settings.colors.success[400],
        pointHoverBackgroundColor: settings.colors.white,
        borderJoinStyle: 'bevel',
        lineTension: .1
      }]
    }

    Charts.create(id, type, options, data)
  }

  // Create Chart
  WeekIQChart('#iqChart')
  TopicIQChart('#topicIqChart')

})()