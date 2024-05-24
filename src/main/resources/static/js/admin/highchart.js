fetch('/api/stats/best-seller')
  .then(response => response.json())
  .then(data => {
    // API 응답에서 데이터 추출
    console.log(data);
    const chartData = data.map(item => ({
      name: item.name, // 상품 이름
      y: item.count // 상품의 판매량
    }));

    // Highcharts 구성 업데이트
    Highcharts.chart('bestseller-container', {
      chart: {
        type: 'column'
      },
      title: {
        text: 'Best Selling Products'
      },
      xAxis: {
        type: 'name',
        labels: {
          autoRotation: [-45, -90],
          style: {
            fontSize: '13px',
            fontFamily: 'Verdana, sans-serif'
          }
        }
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Sales'
        }
      },
      legend: {
        enabled: false
      },
      tooltip: {
        pointFormat: '<b>{point.y}</b> sales'
      },
      series: [{
        name: 'name',
        colorByPoint: true,
        data: chartData,
        dataLabels: {
          enabled: true,
          rotation: -90,
          color: '#FFFFFF',
          inside: true,
          verticalAlign: 'top',
          format: '{point.y}', // 판매량
          y: 10, // 10 pixels down from the top
          style: {
            fontSize: '13px',
            fontFamily: 'Verdana, sans-serif'
          }
        }
      }]
    });
  })
  .catch(error => {
    console.error('데이터 가져오는 중 오류 발생:', error);
  });
