const colors = [
    '#d9480f', '#e8590c', '#f76707', '#fd7e14', '#ff922b', '#ffa94d',
    '#ffc078', '#ffd8a8', '#ffe8cc', '#fff4e6'
]

function drawBestsellerBarChart(series) {

    // Highcharts 구성 업데이트
    Highcharts.chart('bestseller-container', {
        chart: {
            type: 'column'
        },
        title: {
            text: '베스트셀러 10'
        },
        xAxis: {
            categories: [name]
        },
        yAxis: {
            min: 0,
            title: {
                text: '판매량'
            }
        },
        legend: {
            enabled: true
        },
        tooltip: {
            pointFormat: '<b>{point.y}</b> 건'
        },
        series, // [{name: "aa", data: [6]}, ...],

    })
}

function drawJoinRoutePieChart(series) {
    const oddColors = colors.filter((color, index) => index % 2 == 1)

    Highcharts.chart('join-route-container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '가입 경로 분석 그래프',
            align: 'center'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                colors: oddColors,
                borderRadius: 5,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b><br>{point.percentage:.1f} %',
                    distance: -50,
                    filter: {
                        property: 'percentage',
                        operator: '>',
                        value: 4
                    },
                    style: {
                        textOutline: 'none'
                    }
                }
            }
        },
        series //
    })
}

function drawResultOfSalesLineChart(inputData) {
    const oddColors = colors.filter((color, index) => index % 2 == 1);

    console.log("카테고리", inputData.categories);
    let categories = inputData.categories;
    let series = inputData.series;


    Highcharts.chart('sales-container', {
        chart: {
            type: 'spline'
        },
        title: {
            text: '일별 거래 건수 집계 그래프'
        },
        colors: oddColors,
        subtitle: {
            text: '구매/취소/환불/교환'
        },
        xAxis: {
            categories
        },
        yAxis: {
            title: {
                text: 'Temperature'
            },
            labels: {
                format: '{value}°'
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#fff4e6',
                    lineWidth: 1
                }
            }
        },
        series
        // series: [{
        //     name: 'Tokyo',
        //     marker: {
        //         symbol: 'square'
        //     },
        //     data: [5.2, 5.7, 8.7, 13.9, 18.2, 21.4, 25.0, {
        //         y: 26.4,
        //         marker: {
        //             symbol: 'url(https://www.highcharts.com/samples/graphics/sun.png)'
        //         },
        //         accessibility: {
        //             description: 'Sunny symbol, this is the warmest point in the ' +
        //                 'chart.'
        //         }
        //     }, 22.8, 17.5, 12.1, 7.6]
        //
        // }, {
        //     name: 'Bergen',
        //     data: [{
        //         y: 1.5,
        //         marker: {
        //             symbol: 'url(https://www.highcharts.com/samples/graphics/snow.png)'
        //         },
        //         accessibility: {
        //             description: 'Snowy symbol, this is the coldest point in the ' +
        //                 'chart.'
        //         }
        //     }, 1.6, 3.3, 5.9, 10.5, 13.5, 14.5, 14.4, 11.5, 8.7, 4.7, 2.6]
        // }]
    });
}

// 판매량 top 10 차트
fetch('/api/stats/best-seller')
    .then(response => response.json())
    .then(data => {
        chartData = data.map((item, index) => ({
            name: item.prettyName,  // 상품 이름
            data: [item.count],     // 상품의 판매량
            color: colors[index % colors.length]
        }));

        drawBestsellerBarChart(chartData);
    }).catch(error => {
        console.error('데이터 가져오는 중 오류 발생:', error);
    });

// 회원 가입 경로 집계 차트
fetch('/api/stats/join-route')
    .then(response => response.json())
    .then(data => {
        chartData = data.map(item => ({
            name: item.name,        // 유입 경로명
            y: item.count           // 유입 수치
        }));

        const series = [{ name: "점유율", data: chartData }]

        drawJoinRoutePieChart(series);
    }).catch(error => {
    console.error('데이터 가져오는 중 오류 발생:', error);
});

// 일별 구매/취소/환불/교환 건수 집계 차트
fetch('/api/stats/calculate-sales')
    .then(response => response.json())
    .then(data => {
        data = parseOrderChartData(data);
        console.log(data);

        drawResultOfSalesLineChart(data);
    }).catch(error => {
    console.error('데이터 가져오는 중 오류 발생:', error);
});

function parseOrderChartData(data) {
    data = [
        {
            "DATE" : "2024-01-20",
            "CATEGORY" : "주문건수",
            "COUNT" : 3
        },
        {
            "DATE" : "2024-02-04",
            "CATEGORY" : "주문건수",
            "COUNT" : 2
        },
        {
            "DATE" : "2024-04-24",
            "CATEGORY" : "주문건수",
            "COUNT" : 2
        },
        {
            "DATE" : "2024-05-04",
            "CATEGORY" : "주문건수",
            "COUNT" : 3
        },
        {
            "DATE" : "2024-05-17",
            "CATEGORY" : "주문건수",
            "COUNT" : 3
        },
        {
            "DATE" : "2024-05-18",
            "CATEGORY" : "주문건수",
            "COUNT" : 2
        },
        {
            "DATE" : "2024-05-19",
            "CATEGORY" : "주문건수",
            "COUNT" : 4
        },
        {
            "DATE" : "2024-05-22",
            "CATEGORY" : "주문건수",
            "COUNT" : 2
        },
        {
            "DATE" : "2024-05-23",
            "CATEGORY" : "주문건수",
            "COUNT" : 9
        },
        {
            "DATE" : "2024-05-24",
            "CATEGORY" : "주문건수",
            "COUNT" : 4
        },
        {
            "DATE" : "2024-02-04",
            "CATEGORY" : "취소건수",
            "COUNT" : 2
        },
        {
            "DATE" : "2024-05-22",
            "CATEGORY" : "취소건수",
            "COUNT" : 1
        }
    ]

    // xAxis.categories = ['2024-01-20', '2024-02-04', ...]
    let categories = [ ...new Set(data.map(e => e.DATE)) ];

    // series[][name] = ['주문건수', '취소건수', '환불건수', '교환건수']
    let labels = [ ...new Set(data.map(e => e.CATEGORY)) ];

    let series = []
    for (let label of labels) {
        // [{"DATE":"2024-02-04","CATEGORY":"취소건수","COUNT":2},{"DATE":"2024-05-22","CATEGORY":"취소건수","COUNT":1}]
        let labelData = data.filter(e => e.CATEGORY == label);
        labelData.find(e => e.DATE == "2024-01-04")
        series.push({

            data: categories
                    .map(date => labelData.find(e => e.DATE == date)) // date == '2024-02-04'
                    .map(e => e && e.COUNT || 0), // [0, 2, 0, 0, 0, 0, 0, 1, 0, 0]
            name: label
        })
    }

    return {
        categories,
        series
    };
}