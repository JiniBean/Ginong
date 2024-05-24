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
        series // [{name: "aa", data: [6]}, ...]
    })
}

function drawJoinRoutePieChart(series) {
    const colors = Highcharts.getOptions().colors.map((c, i) =>
        // Start out with a darkened base color (negative brighten), and end
        // up with a much brighter color
        Highcharts.color(Highcharts.getOptions().colors[0])
            .brighten((i - 3) / 7)
            .get()
    );

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
                colors,
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

// 판매량 top 10 차트
fetch('/api/stats/best-seller')
    .then(response => response.json())
    .then(data => {
        // API 응답에서 데이터 추출
        console.log(data);
        chartData = data.map(item => ({
            name: item.prettyName,  // 상품 이름
            data: [item.count]        // 상품의 판매량
        }));

        drawBestsellerBarChart(chartData);
    }).catch(error => {
        console.error('데이터 가져오는 중 오류 발생:', error);
    });



// 회원 가입 경로 집계 차트
fetch('/api/stats/join-route')
    .then(response => response.json())
    .then(data => {
        // API 응답에서 데이터 추출
        console.log(data);
        data = [
            {
                "name": "SNS",
                "count": 20
            },
            {
                "name": "지인추천",
                "count": 10
            },
            {
                "name": "인터넷",
                "count": 9
            },
            {
                "name": "블로그",
                "count": 2
            }
        ]

        chartData = data.map(item => ({
            name: item.name,        // 유입 경로명
            y: item.count         // 유입 수치
        }));

        const series = [{ name: "광고", data: chartData }]

        drawJoinRoutePieChart(series);
    }).catch(error => {
    console.error('데이터 가져오는 중 오류 발생:', error);
});