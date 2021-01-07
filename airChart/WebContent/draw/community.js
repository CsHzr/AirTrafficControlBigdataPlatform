function comm1() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    var option = {
        title: [{
                text: '今日煤矿煤炭产品销售预览',
                x: 'center',
                textStyle: {
                    fontSize: 20
                }
            },
            {
                text: '内外块粉比',
                x: '19.8%',
                y: '45%',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontSize: 20
                }
            },
            {
                text: '块粉比',
                x: '49.8%',
                y: '45%',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontSize: 20
                }
            },
            {
                text: '内外比',
                x: '79.8%',
                y: '45%',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontSize: 20
                }
            },
            {
                text: '34106.24吨',
                x: 'center',
                y: '8%',
                subtext: '2019-05-20 15:19:32',
                textStyle: {
                    fontSize: 80
                }
            }
        ],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: [{
                data: ['外销', '内销'],
                x: '25%',
                y: '60%'
            },
            {
                data: ['外销块煤', '内销块煤', '外销面煤', '内销面煤', '块煤', '面煤'],
                x: 'left',
                orient: 'vertical'
            }
        ],
        grid: [{
                left: '0%',
                right: '55%',
                top: '65%',
                bottom: '5%',
                containLabel: true
            },
            {
                gridindex: 1,
                left: '48%',
                right: '3%',
                top: '65%',
                bottom: '5%',
                containLabel: true
            }
        ],
        xAxis: [{
                type: 'value',
                axisLabel: {
                    formatter: '{value} 吨'
                },
                boundaryGap: [0, 0.01]
            },
            {
                gridIndex: 1,
                type: 'category',
                boundaryGap: false,
                data: ['0时', '1时', '2时', '3时', '4时', '5时', '6时', '7时', '8时', '9时', '10时', '11时', '12时', '13时', '14时', '15时', '16时', '17时', '18时', '19时', '20时', '21时', '22时', '23时']
            }
        ],
        yAxis: [{
                type: 'category',
                data: ['块煤大块', '块煤4-8', '块煤3-6', '块煤2-4', '块煤1-3', '块煤0.5-1', '面煤', '电面煤', '电块煤', '劣质煤'],
                axisLabel: {
                    interval: 0
                }
            },
            {
                gridIndex: 1,
                type: 'value',
                axisLabel: {
                    formatter: '{value} 吨'
                }
            }
        ],
        series: [{
                name: '外销',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },
                data: [1576.28, 840.18, 997.15, 1865.25, 3026.62, 877.09, 6361.68, 0, 0, 1188.28]
            },
            {
                name: '内销',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },
                data: [44.67, 11.94, 123.67, 20.28, 62.46, 29.62, 1362.29, 10127.00, 3909.78, 1682.00]
            },
            {
                name: '大煤种',
                type: 'pie',
                center: ['20%', '45%'],
                radius: ['15%', '20%'],
                label: {
                    normal: {
                        formatter: '{b} :{c}吨({d}%)'
                    }
                },
                data: [{
                        value: 9182.57,
                        name: '外销块煤'
                    },
                    {
                        value: 4202.42,
                        name: '内销块煤'
                    },
                    {
                        value: 6361.68,
                        name: '外销面煤'
                    },
                    {
                        value: 11489.29,
                        name: '内销面煤'
                    }
                ]
            },
            {
                name: '块粉比',
                type: 'pie',
                center: ['50%', '45%'],
                radius: ['15%', '20%'],
                label: {
                    normal: {
                        formatter: '{b} :{c}吨({d}%)'
                    }
                },
                data: [{
                        value: 13384.99,
                        name: '块煤'
                    },
                    {
                        value: 17850.97,
                        name: '面煤'
                    }
                ]
            },
            {
                name: '内外比',
                type: 'pie',
                center: ['80%', '45%'],
                radius: ['15%', '20%'],
                label: {
                    normal: {
                        formatter: '{b} :{c}吨({d}%)'
                    }
                },
                data: [{
                        value: 15544.25,
                        name: '外销'
                    },
                    {
                        value: 15691.71,
                        name: '内销'
                    }
                ]
            },
            {
                xAxisIndex: 1,
                yAxisIndex: 1,
                name: '分时统计',
                type: 'line',
                lineStyle: {
                    normal: {
                        color: '#4ea397'
                    }
                },
                data: ['516.48', '482.46', '418.74', '284.66', '315.28', '365.34', '657.19', '3054.78', '4127.51', '5180.03', '5416.46', '4113.99', '3021.61', '4191.89', '3437.5', '1250.22', ],
                markPoint: {
                    data: [{
                            type: 'max',
                            name: '最大值',
                            symbolSize: 80
                        },
                        {
                            type: 'min',
                            name: '最小值',
                            symbolSize: 50
                        }
                    ],
                    itemStyle: {
                        normal: {
                            color: '#d0648a'
                        }
                    }
                },
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }
            }
        ]
    };
    myChart.setOption(option);
}
function comm2() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    var getY = function(x) {
        let y = Math.sqrt((1 - Math.pow(x / 38, 2)) * Math.pow(30, 2));
        return y;
    };
    var getOutY = function(x) {
        let y = Math.sqrt((1 - Math.pow(x / 50, 2)) * Math.pow(42, 2));
        return y;
    };
    var items = [{

            symbol: "",
            name: "市监狱局",
            value: [5, getOutY(5)],
            pointType: "point",
            belong: "南开区"
        },
        {

            symbol: "",
            name: "南开区政府",
            value: [7.5, getOutY(7.5)],
            pointType: "point",
            belong: "南开区"
        },
        {

            symbol: "",
            name: "市税务局",
            value: [10, getOutY(10)],
            pointType: "point",
            belong: "河北区"
        },
        {

            symbol: "",
            name: "河北区政府",
            value: [12.5, getOutY(12.5)],
            pointType: "point",
            belong: "河北区"
        },
        {

            symbol: "",
            name: "市公安局",
            value: [15, getOutY(15)],
            pointType: "point",
            belong: "西青区"
        },
        {

            symbol: "",
            name: "西青区政府",
            value: [17.5, getOutY(17.5)],
            pointType: "point",
            belong: "西青区"
        },
        {

            symbol: "",
            name: "市税务局数据中心",
            value: [20, getOutY(20)],
            pointType: "point",
            belong: "空港IDC"
        },
        {

            symbol: "",
            name: "空港数据中心",
            value: [22.5, getOutY(22.5)],
            pointType: "point",
            belong: "空港IDC"
        },
        {

            symbol: "",
            name: "武清区政府",
            value: [25, getOutY(25)],
            pointType: "point",
            belong: "武清区"
        },
        {

            symbol: "",
            name: "市惩防教育基地",
            value: [27.5, getOutY(27.5)],
            pointType: "point",
            belong: "市电子政务中心"
        },
        {

            symbol: "",
            name: "市教委",
            value: [30, getOutY(30)],
            pointType: "point",
            belong: "市电子政务中心"
        },
        {

            symbol: "",
            name: "市残联",
            value: [32.5, getOutY(32.5)],
            pointType: "point",
            belong: "市电子政务中心"
        },
        {

            symbol: "",
            name: "市党委校",
            value: [35, getOutY(35)],
            pointType: "point",
            belong: "市电子政务中心"
        },
        {

            symbol: "",
            name: "市城管委",
            value: [37.5, getOutY(37.5)],
            pointType: "point",
            belong: "市电子政务中心"
        },
        {

            symbol: "",
            name: "市生态环境局",
            value: [40, getOutY(40)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [10, 5]
            }
        },
        {

            symbol: "",
            name: "市水务局",
            value: [42.5, getOutY(42.5)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [12, 8]
            }
        },
        {

            symbol: "",
            name: "市规自局",
            value: [45, getOutY(45)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [15, 10]
            }
        },
        {

            symbol: "",
            name: "市卫健局",
            value: [47.2, getOutY(47.2)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [15, 15]
            }
        },
        {

            symbol: "",
            name: "市应急局",
            value: [49, getOutY(49)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [15, 15]
            }
        },
        {

            symbol: "",
            name: "市气象局",
            value: [49.95, getOutY(49.95)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [15, 20]
            }
        },
        {

            symbol: "",
            name: "市合作交流办",
            value: [49.6, -getOutY(49.6)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [15, -15]
            }
        },
        {

            symbol: "",
            name: "市交通运输委",
            value: [48.2, -getOutY(48.2)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [12, -10]
            }
        },
        {

            symbol: "",
            name: "市司法局",
            value: [46.3, -getOutY(46.3)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [12, -8]
            }
        },
        {

            symbol: "",
            name: "市高法",
            value: [44, -getOutY(44)],
            pointType: "point",
            belong: "市电子政务中心",
            label: {
                show: true,
                offset: [10, -5]
            }
        },
        {

            symbol: "",
            name: "东丽区政府",
            value: [41, -getOutY(41)],
            pointType: "point",
            belong: "东丽区",
            label: {
                show: true,
                offset: [10, -5]
            }
        },
        {

            symbol: "",
            name: "宝坻区政府",
            value: [38, -getOutY(38)],
            pointType: "point",
            belong: "宝坻区",
            label: {
                show: true,
                offset: [10, -5]
            }
        },
        {

            symbol: "",
            name: "市科技局",
            value: [35, -getOutY(35)],
            pointType: "point",
            belong: "和平区",
            label: {
                show: true,
                offset: [10, -5]
            }
        },
        {

            symbol: "",
            name: "市统计局",
            value: [32, -getOutY(32)],
            pointType: "point",
            belong: "和平区",
            label: {
                show: true,
                offset: [10, -5]
            }
        },
        {

            symbol: "",
            name: "和平区政府",
            value: [29, -getOutY(29)],
            pointType: "point",
            belong: "和平区"
        },
        {

            symbol: "",
            name: "蓟州区政府",
            value: [26, -getOutY(26)],
            pointType: "point",
            belong: "蓟州区"
        },
        {

            symbol: "",
            name: "河东区政府",
            value: [23, -getOutY(23)],
            pointType: "point",
            belong: "河东区"
        },
        {

            symbol: "",
            name: "市人防办",
            value: [20, -getOutY(20)],
            pointType: "point",
            belong: "河东区"
        },
        {

            symbol: "",
            name: "市粮食和物资储备局",
            value: [17, -getOutY(17)],
            pointType: "point",
            belong: "河东区"
        },
        {

            symbol: "",
            name: "市场监管委",
            value: [14, -getOutY(14)],
            pointType: "point",
            belong: "河东区"
        },
        {

            symbol: "",
            name: "河西区政府",
            value: [11, -getOutY(11)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "市戒毒局",
            value: [8, -getOutY(8)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "市安全局",
            value: [5, -getOutY(5)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "市农委",
            value: [2, -getOutY(2)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "市文旅局",
            value: [-1, -getOutY(-1)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "市纪检委",
            value: [-4, -getOutY(-4)],
            pointType: "point",
            belong: "河西区"
        },
        {

            symbol: "",
            name: "静海区政府",
            value: [-7, -getOutY(-7)],
            pointType: "point",
            belong: "静海区"
        },
        {

            symbol: "",
            name: "北辰区政府",
            value: [-10, -getOutY(-10)],
            pointType: "point",
            belong: "北辰区"
        },
        {

            symbol: "",
            name: "红桥区政府",
            value: [-13, -getOutY(-13)],
            pointType: "point",
            belong: "红桥区"
        },
        {

            symbol: "",
            name: "滨海新区政府",
            value: [-16, -getOutY(-16)],
            pointType: "point",
            belong: "滨海新区"
        },
        {

            symbol: "",
            name: "宁河区政府",
            value: [-19, -getOutY(-19)],
            pointType: "point",
            belong: "宁河区"
        },
        {

            symbol: "",
            name: "市退役军人局",
            value: [-22, -getOutY(-22)],
            pointType: "point",
            belong: "烟台道联通机房"
        },
        {

            symbol: "",
            name: "市公积金中心",
            value: [-25, -getOutY(-25)],
            pointType: "point",
            belong: "烟台道联通机房"
        },
        {

            symbol: "",
            name: "市人社局",
            value: [-28, -getOutY(-28)],
            pointType: "point",
            belong: "烟台道联通机房"
        },
        {

            symbol: "",
            name: "市发改委",
            value: [-31, -getOutY(-31)],
            pointType: "point",
            belong: "烟台道联通机房"
        },
        {

            symbol: "",
            name: "市国安委",
            value: [-34, -getOutY(-34)],
            pointType: "point",
            belong: "烟台道联通机房",
        },
        {

            symbol: "",
            name: "市政法委",
            value: [-36, -getOutY(-36)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-10, -5]
            }
        },
        {

            symbol: "",
            name: "市财政局",
            value: [-39, -getOutY(-39)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-10, -8]
            }
        },
        {

            symbol: "",
            name: "市金融局",
            value: [-42, -getOutY(-42)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-10, -8]
            }
        },
        {

            symbol: "",
            name: "市医保局",
            value: [-45, -getOutY(-45)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-12, -10]
            }
        },
        {

            symbol: "",
            name: "市信访办",
            value: [-47.5, -getOutY(-47.5)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-12, -12]
            }
        },
        {

            symbol: "",
            name: "市住建局",
            value: [-49.3, -getOutY(-49.3)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-15, -15]
            }
        },
        {

            symbol: "",
            name: "市商务局",
            value: [-49.99, getOutY(-49.99)],
            pointType: "point",
            belong: "烟台道联通机房",
            label: {
                show: true,
                offset: [-15, -18]
            }
        },
        {

            symbol: "",
            name: "市委组织部",
            value: [-49, getOutY(-49)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-15, 20]
            }
        },
        {

            symbol: "",
            name: "市政府服务办",
            value: [-47.2, getOutY(-47.2)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-15, 15]
            }
        },
        {

            symbol: "",
            name: "市信息中心",
            value: [-45, getOutY(-45)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-15, 15]
            }
        },
        {

            symbol: "",
            name: "市工信局",
            value: [-42.5, getOutY(-42.5)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-15, 10]
            }
        },
        {

            symbol: "",
            name: "市高检",
            value: [-40, getOutY(-40)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-12, 8]
            }
        },
        {

            symbol: "",
            name: "市机关管理局",
            value: [-37.5, getOutY(-37.5)],
            pointType: "point",
            belong: "市政府",
            label: {
                show: true,
                offset: [-10, 5]
            }
        },
        {

            symbol: "",
            name: "市编办",
            value: [-35, getOutY(-35)],
            pointType: "point",
            belong: "市政府"
        },
        {

            symbol: "",
            name: "市机要局",
            value: [-32.5, getOutY(-32.5)],
            pointType: "point",
            belong: "市政府"
        },
        {

            symbol: "",
            name: "市政府办公厅",
            value: [-30, getOutY(-30)],
            pointType: "point",
            belong: "市政府"
        },
        {

            symbol: "",
            name: "市委网信办",
            value: [-27.5, getOutY(-27.5)],
            pointType: "point",
            belong: "市政府"
        },
        {

            symbol: "",
            name: "市委办公厅",
            value: [-25, getOutY(-25)],
            pointType: "point",
            belong: "市政府"
        },
        {

            symbol: "",
            name: "市财政局数据中心",
            value: [-22.5, getOutY(-22.5)],
            pointType: "point",
            belong: "市人大"
        },
        {

            symbol: "",
            name: "市政协办公厅",
            value: [-20, getOutY(-20)],
            pointType: "point",
            belong: "市政协"
        },
        {

            symbol: "",
            name: "津南区政府",
            value: [-17.5, getOutY(-17.5)],
            pointType: "point",
            belong: "津南区"
        },
        {

            symbol: "",
            name: "市人大办公厅",
            value: [-15, getOutY(-15)],
            pointType: "point",
            belong: "津南区"
        },
        {

            symbol: "",
            name: "华苑数据中心",
            value: [-12.5, getOutY(-12.5)],
            pointType: "point",
            belong: "华苑IDC"
        },
        {

            symbol: "",
            name: "市民政局",
            value: [-10, getOutY(-10)],
            pointType: "point",
            belong: "华苑IDC"
        },
        {

            symbol: "",
            name: "市审计局",
            value: [-7.5, getOutY(-7.5)],
            pointType: "point",
            belong: "科技信息所"
        },
        {

            symbol: "",
            name: "市国资委",
            value: [-5, getOutY(-5)],
            pointType: "point",
            belong: "科技信息所"
        },
        {

            symbol: "",
            name: "南开区",
            value: [6, getY(6)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "河北区",
            value: [12, getY(12)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "西青区",
            value: [18, getY(18)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "空港IDC",
            value: [24, getY(24)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "武清区",
            value: [30, getY(30)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "市电子政务中心",
            value: [36, getY(36)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "东丽区",
            value: [36, -getY(36)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "宝坻区",
            value: [30, -getY(30)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "和平区",
            value: [24, -getY(24)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "蓟州区",
            value: [18, -getY(18)],
            pointType: "cloud"
        },
        {
            level: 4,
            symbol: "",
            name: "河东区",
            value: [11, -getY(11)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "河西区",
            value: [4, -getY(4)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "静海区",
            value: [-4, -getY(-4)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "北辰区",
            value: [-11, -getY(-11)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "红桥区",
            value: [-18, -getY(-18)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "滨海新区",
            value: [-24, -getY(-24)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "宁河区",
            value: [-30, -getY(-30)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "烟台道联通机房",
            value: [-36, -getY(-36)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "市政府",
            value: [-36, getY(-36)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "市人大",
            value: [-30, getY(-30)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "市政协",
            value: [-24, getY(-24)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "津南区",
            value: [-18, getY(-18)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "华苑IDC",
            value: [-12, getY(-12)],
            pointType: "cloud"
        },
        {

            symbol: "",
            name: "科技信息所",
            value: [-6, getY(-6)],
            pointType: "cloud"
        }
    ];

    items.forEach((el, index) => {
        if (el.pointType == "cloud") {
            el.symbol = "image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADYAAAAhCAYAAACSllj+AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OTg5MEVERTdBNUZGMTFFOEIzNERDMzk3QjJGOEQ3REMiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTg5MEVERThBNUZGMTFFOEIzNERDMzk3QjJGOEQ3REMiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo5ODkwRURFNUE1RkYxMUU4QjM0REMzOTdCMkY4RDdEQyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo5ODkwRURFNkE1RkYxMUU4QjM0REMzOTdCMkY4RDdEQyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PkQLbgwAAAtrSURBVHja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",
                el.symbolSize = [56, 35];
            el.label = {
                normal: {
                    show: true,
                    position: "bottom",
                    borderWidth: 1,
                    borderRadius: 12,
                    padding: [4, 8, 4, 8],
                    distance: 10,
                    color: "rgb(255,255,255)",
                    borderColor: "rgb(89,197,238)"
                }
            };
        } else if (el.pointType == "point") {
            el.itemStyle = {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: "#0ceffd"
                    },
                    {
                        offset: 1,
                        color: "#0fffff"
                    }
                ])
            };
            if (!el.label) {
                el.label = {
                    show: true
                }
            }
            el.label.width = 200
            el.label.color = {
                lineColor: {
                    color: "rgb(24,163,239)"
                }
            }
            if (index < 38) {
                el.label.rotate = 57 - 3 * index
                el.label.align = "left"
                if (!el.label.offset) {
                    el.label.offset = [8, 0]
                }
                if (index < 20) {
                    el.label.position = 'top'
                } else {
                    el.label.position = 'bottom'
                }
            } else {
                el.label.rotate = (-57 + 3 * (75 - index))
                el.label.align = "right"
                if (!el.label.offset) {
                    el.label.offset = [-8, 0]
                }
                if (index > 56) {
                    el.label.position = 'top'
                } else {
                    el.label.position = 'bottom'
                }
            }

        }
    });
    const dataArr = []
    const targetCoord = [0, 0];
    items.forEach(el => {
        if (el.belong) {
            items.forEach(element => {
                if (el.belong == element.name) {
                    dataArr.push([{
                            coord: element.value
                        },
                        {
                            coord: el.value
                        }
                    ]);
                }
            });
        } else if (el.pointType != 'none') {
            dataArr.push([{
                    coord: targetCoord
                },
                {
                    coord: el.value
                }
            ]);
        }
    });
    var option = {
        backgroundColor:'#111',
        legend: [],
        xAxis: {
            show: false,
            type: "value",
            max: 50,
            min: -51
        },
        yAxis: {
            show: false,
            type: "value",
            max: 50,
            min: -50
        },
        series: [{
                type: "graph",
                layout: "none",
                coordinateSystem: "cartesian2d",
                symbolSize: [15, 15],
                z: 3,
                circular: {
                    rotateLabel: true
                },

                itemStyle: {
                    normal: {
                        shadowColor: "none"
                    }
                },
                data: items
            },
            {
                name: "",
                type: "lines",
                coordinateSystem: "cartesian2d",
                z: 1,
                effect: {
                    show: true,
                    smooth: false,
                    trailLength: 0,
                    symbol: "image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAhCAYAAADtR0oPAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAZdEVYdFNvZnR3YXJlAEFkb2JlIEltYWdlUmVhZHlxyWU8AAAB2ElEQVQ4T32USU7DQBBFbcKMAAFigcSSg3ADtmzZc0juwQ6JMA9hCBAG81/Rv9W2HEp6cae7fk1tuW6appLVCWwmPTEOw0H2ww8CHHEaiLmERTh9ic/0bBBwiNOiWE7MCwynsXgVH2KCYFYLnNfFhtgSiMj8Lu4TIzFGQLQ1sS12xZFA8CZuxYk4FVdiRDmwIBDtiH1xIA7FsdgTZF4SA5xJTcNE3RSeio1gq4I+a08DAaWtiBhfMpqmP0T0mgXlaL/ZSMY4icx+3JMFRKUUnozPNhHs+TzuwBtEJiJztzEpAlBaXBwCR0bAIZdkQ2xBBC4FbJLhWdhehAUEzBn4A9SMkw0xe87QKolNDh+FjTUZyEzAXJKzcMA7Y0PgDFNLehC2O9HbQ5mBHnDAeEvLKYUA448z8EozTvZ4W8sessAZEHBZ3AWi8h6ikq6ASGSgrCfRvbj8LiFgk0MEzJ9psfaU8Gm9fGVJRKdhMljQyoCx6ZIYLSN1hmhYtEpik5LIgPNNWpdNt0pik2hEZZyXad1bkpt2D0S/EGUPvSW5Bz4pQ0E5UzN4Sjhdi3Pxb9NEcQbqPxNuGEGYv94Iux9ll1ESH2M9wywyWDj9LWOdM9hKZxsOyamqfgG1ZQ8JFbfSTwAAAABJRU5ErkJggg==",
                    symbolSize: [10, 30],
                    period: 4,
                    delay: 2
                },

                lineStyle: {
                    width: 2,
                    color: 'rgb(255,255,255)',
                    curveness: 0
                },
                data: dataArr
            }
        ]
    };
    myChart.setOption(option);
}
function comm3() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: ['互联网',
    '大型企业',
    '金融',
    '其它',
    '央企',
    '政府',
    '传统媒体',
    '资讯',
    '阅读',
    '电商',
    '视频',
    '自媒体',
    '其它2',
    '科技',
    '社区',
    '平台',
    '综合',
    '直播',
    '工具',
    '制造业',
    '民营上市公司',
    '房地产',
    '外资企业',
    '其它3',
    '银行',
    '证券',
    '保险',
    '基金',
    '其它1',
    '科技1',
    '军工',
    '运营商',
    '建筑',
    '政府',
    '报业',
    '广电系']
        },
        series: [
            {
                name: '一级行业',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    position: 'inner'
                },
                labelLine: {
                    show: false
                },
                data: [
    {value: 147, name: '互联网', selected: true},
    {value: 29, name: '金融'},
    {value: 22, name: '政企'},
    {value: 14, name: '传统'},
    {value: 11, name: '军工'},
    {value: 9, name: '媒体'},
    {value: 8, name: '咨询'},
    {value: 6, name: '其它'},
    {value: 8, name: '通信'},
    {value: 2, name: '法律'},
    {value: 2, name: '房地产'}
                ]
            },
            {
                name: '二级行业',
                type: 'pie',
                radius: ['40%', '55%'],
                label: {
                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                    backgroundColor: '#eee',
                    borderColor: '#aaa',
                    borderWidth: 1,
                    borderRadius: 4,
                    // shadowBlur:3,
                    // shadowOffsetX: 2,
                    // shadowOffsetY: 2,
                    // shadowColor: '#999',
                    // padding: [0, 7],
                    rich: {
                        a: {
                            color: '#999',
                            lineHeight: 22,
                            align: 'center'
                        },
                        // abg: {
                        //     backgroundColor: '#333',
                        //     width: '100%',
                        //     align: 'right',
                        //     height: 22,
                        //     borderRadius: [4, 4, 0, 0]
                        // },
                        hr: {
                            borderColor: '#aaa',
                            width: '100%',
                            borderWidth: 0.5,
                            height: 0
                        },
                        b: {
                            fontSize: 16,
                            lineHeight: 33
                        },
                        per: {
                            color: '#eee',
                            backgroundColor: '#334455',
                            padding: [2, 4],
                            borderRadius: 2
                        }
                    }
                },
                data: [
    {value: 31, name: '资讯'},
    {value: 19, name: '电商'},
    {value: 19, name: '阅读'},
    {value: 15, name: '视频'},
    {value: 13, name: '其它'},
    {value: 10, name: '科技'},
    {value: 10, name: '自媒体'},
    {value: 8, name: '平台'},
    {value: 7, name: '社区'},
    {value: 7, name: '综合'},
    {value: 6, name: '直播'},
    {value: 2, name: '工具'},
    {value: 15, name: '银行'},
    {value: 6, name: '保险'},
    {value: 6, name: '证券'},
    {value: 1, name: '基金'},
    {value: 1, name: '审计'},
    {value: 10, name: '政府'},
    {value: 8, name: '大型企业'},
    {value: 4, name: '服务商'},
    {value: 14, name: '制造业'},
    {value: 11, name: '军工'},
    {value: 5, name: '日报'},
    {value: 4, name: '广电'},
    {value: 8, name: '咨询'},
    {value: 3, name: '其它'},
    {value: 1, name: '服务'},
    {value: 1, name: '医疗'},
    {value: 1, name: '制药'},
    {value: 4, name: '运营商'},
    {value: 4, name: '制造商'},
    {value: 2, name: '法律'},
    {value: 2, name: '房地产商'}

                ]
            }
        ]
    };
    
    myChart.setOption(option);
}
function comm4() {
    echarts.dispose(document.getElementById('main')); 
    
        var myChart = echarts.init(document.getElementById('main'), 'vintage');

        sourceData = [{'name': '澳门特别行政区', 'value': 30, 'extData': [89, '#ccfd66', 0, '']},
    {'name': '西藏自治区', 'value': 33, 'extData': [367, '#cdf967', 0, '']},
    {'name': '青海省', 'value': 38, 'extData': [809, '#cff169', 0, '']},
    {'name': '宁夏回族自治区', 'value': 40, 'extData': [1023, '#cfee69', 0, '']},
    {'name': '香港特别行政区', 'value': 40, 'extData': [1070, '#d0ed6a', 0, '']},
    {'name': '海南省', 'value': 41, 'extData': [1179, '#d0eb6a', 0, '']},
    {'name': '天津市', 'value': 51, 'extData': [2105, '#d3db6d', 0, '']},
    {'name': '新疆维吾尔自治区', 'value': 53, 'extData': [2364, '#d4d76e', 0, '']},
    {'name': '上海市', 'value': 59, 'extData': [2965, '#d6cd70', 0, '']},
    {'name': '北京市', 'value': 62, 'extData': [3237, '#d7c871', 0, '']},
    {'name': '甘肃省', 'value': 63, 'extData': [3375, '#d7c671', 0, '']},
    {'name': '贵州省', 'value': 64, 'extData': [3467, '#d8c472', 0, '']},
    {'name': '吉林省', 'value': 67, 'extData': [3744, '#d9bf73', 0, '']},
    {'name': '重庆市', 'value': 70, 'extData': [4005, '#dabb74', 0, '']},
    {'name': '内蒙古自治区', 'value': 70, 'extData': [4040, '#daba74', 0, '']},
    {'name': '云南省', 'value': 75, 'extData': [4510, '#dbb275', 0, '']},
    {'name': '黑龙江省', 'value': 77, 'extData': [4777, '#dcae76', 0, '']},
    {'name': '广西壮族自治区', 'value': 80, 'extData': [5024, '#ddaa77', 0, '']},
    {'name': '陕西省', 'value': 81, 'extData': [5174, '#dea778', 0, '']},
    {'name': '山西省', 'value': 81, 'extData': [5179, '#dea778', 0, '']},
    {'name': '福建省', 'value': 84, 'extData': [5463, '#dfa279', 0, '']},
    {'name': '江西省', 'value': 84, 'extData': [5469, '#dfa279', 0, '']},
    {'name': '辽宁省', 'value': 93, 'extData': [6317, '#e1947b', 0, '']},
    {'name': '安徽省', 'value': 94, 'extData': [6445, '#e2917c', 0, '']},
    {'name': '湖北省', 'value': 95, 'extData': [6583, '#e28f7c', 0, '']},
    {'name': '湖南省', 'value': 101, 'extData': [7172, '#e4857e', 0, '']},
    {'name': '河北省', 'value': 115, 'extData': [8533, '#e96e83', 0, '']},
    {'name': '浙江省', 'value': 123, 'extData': [9370, '#ec6086', 0, '']},
    {'name': '四川省', 'value': 124, 'extData': [9461, '#ec5e86', 0, '']},
    {'name': '河南省', 'value': 126, 'extData': [9612, '#ed5c87', 0, '']},
    {'name': '江苏省', 'value': 131, 'extData': [10140, '#ee5388', 0, '']},
    {'name': '山东省', 'value': 145, 'extData': [11558, '#f33b8d', 0, '']},
    {'name': '广东省', 'value': 152, 'extData': [12260, '#f62f90', 0, '']}]


    graphicData = [{
        type: 'group',
        left: 'center',
        top: '60%',
        bounding: 'raw', //重要，否则内容无法超过组
        z: 100,
        children: []
    }]
    graphicScatter = {
        type: 'circle',
        shape: {
            r: 2
        },
        style: {
            fill: 'white'
        },
        z: 100
    }
    graphicText = [{
        type: 'text',
        // left: 'center',
        // top: 10,
        z: 100,
        style: {
            fill: 'white',
            text: '韩国',
            font: 'bold 16px Microsoft YaHei',
            textAlign: 'center'
        }
    }, {
        type: 'text',
        // left: 'center',
        // top: 40,
        z: 100,
        style: {
            fill: 'white',
            text: '5766例',
            font: 'bold 14px Microsoft YaHei',
            textAlign: 'center',

        }
    }, {
        type: 'text',
        // left: 'center',
        // top: 70,
        z: 100,
        style: {
            fill: 'white',
            text: '死亡36例',
            font: 'bold 12px Microsoft YaHei',
            textAlign: 'center'
        }
    }]
    graphic_total_Text = [{
        type: 'text',
        right: -220,
        bottom: 500,
        z: 100,
        style: {
            fill: 'black',
            text: '数据时间2020年3月',
            font: 'bold 12px Microsoft YaHei',
            textAlign: 'right'
        }
    }, {
        type: 'text',
        right: -220,
        bottom: 475,
        z: 100,
        style: {
            fill: 'black',
            text: '中国有166886个银行网点',
            font: 'bold 18px Microsoft YaHei',
            textAlign: 'right',

        }
    }]
    graphicChildren = {
        type: 'group',
        // left: 'center',
        // top: 'center',
        position: [],
        bounding: 'raw',
        z: 100,
        children: []

    }

    function convertData1() {
        var maxScale = 1
        var minScale = 0.1
        var stepRadius = 2 * Math.PI / sourceData.length
        var stepScale = (maxScale - minScale) / sourceData.length
        for (var i = 0; i < sourceData.length; i++) {
            sourceData[i].itemStyle = {
                color: sourceData[i]["extData"][1],
                borderWidth: 0
            }
            sourceData[i].label = {
                show: i > 6 ? false : false,
                position: i <= 5 ? "outer" : "inside",
                // alignTo: "labelLine",
                align: "right",
                borderWidth: 1,
                borderColor: "red",
                alignTo: "edge",
                margin: 650,
                formatter: (a) => {
                    return a.data.extData[3]
                }
            }
            sourceData[i].labelLine = {
                show: i > 6 ? false : false,
                // length: i <= 5 ? 5 * (i - 5) : 0,
                // length2: i <= 5 ? 0 : 0,
                lineStyle: {
                    type: "dashed"
                }
            }
            var curRadius = (i + 0.5) * stepRadius
            // var curArclen = 1
            var curScale = i > 11 ? minScale + stepScale * (i) : 0.6 + stepScale * (i)
            var startR = i > 11 ? sourceData[i]["value"] * 450 / sourceData.slice(-1)[0]["value"] * 0.95 : ((
                sourceData[i]["value"] * 460 / sourceData.slice(-1)[0]["value"] + 20))
            var curR = [startR, startR * (i > 33 ? 0.95 : (i > 11 ? 0.75 : 1.12)), startR * (i > 33 ? 0.95 : (i >
                11 ? 0.8 : 1.17)) * (i > 33 ? 0.95 :
                (i > 11 ? 0.8 : 1.17))]
            var curPositions = []
            var curChilds = []
            var curCircles = []
            for (var j = 0; j < 3; j++) {
                var curX = Math.sin(curRadius) * curR[j]
                var curY = -Math.cos(curRadius) * curR[j]
                curPositions.push([curX, curY])
                var curChild = JSON.stringify(graphicChildren)
                curChild = JSON.parse(curChild)
                curChild.position = [curX, curY]
                curChild.rotation = i > 33 ? -((i + 0.5) / sourceData.length * 2 * Math.PI) : (i <= 11 ? -((i +
                    0.5) / sourceData.length * 2 * Math.PI) + Math.PI / 2 : 0)
                curChild.scale = [curScale, curScale]
                curgraphicText = JSON.parse(JSON.stringify(graphicText[j]))
                curgraphicText.style.text = j == 0 ? sourceData[i]["name"] : (j == 1 ? (i > 11 ? sourceData[i][
                    "extData"
                ][0] + "网点" : "") : (j == 2 && i > 33 ? (sourceData[i][
                    "extData"
                ][2] + "个") : (i <= 11 ? sourceData[i][
                    "extData"
                ][3] : "")))
                // curgraphicText.style.text = j == 0 ? sourceData[i]["name"] : (j == 1 ? i > 11 ? sourceData[i][
                //     "extData"
                // ][0] + "例" : "" : i > 33 ? sourceData[i][
                //     "extData"
                // ][2] + "例" : "")
                if (i <= 11) {
                    curgraphicText.style.fill = "black"
                    curgraphicText.style.textAlign = "left"
                }
                curChild.children = [curgraphicText]
                curChilds.push(curChild)
                // var curCircle = JSON.parse(JSON.stringify(graphicScatter))
                // curCircle.position = [curX, curY]
                // curCircles.push(curCircle)
                // graphicData[0].children.push(curCircle)
                graphicData[0].children.push(curChild)
            }
            console.log(i, sourceData[i]["name"], curRadius, curR, curPositions)
        }
        for (var m = 0; m < 3 * 2; m++) {
            var cur_total_text = graphic_total_Text[m]
            graphicData[0].children.push(cur_total_text)
        }
        return sourceData
    }



    option = {
        title: {
            text: '全国商业银行网点分布',
            // subtext: '纯属虚构',
            left: 'center',
            textStyle: {
                //color: "red",
                fontSize: 40
            },
            //backgroundColor: "rgb(199,16,16)",
            top: "2%"
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // legend: {
        //     left: 'center',
        //     top: 'bottom',
        //     data: ['rose1', 'rose2', 'rose3', 'rose4', 'rose5', 'rose6', 'rose7', 'rose8']
        // },
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        graphic: graphicData,
        series: [{
            name: '银行网点个数',
            type: 'pie',
            radius: [20, 450],
            center: ['50%', '60%'],
            label: {
                show: true
            },
            roseType: 'area',
            data: convertData1(),
            rich: {
                a: {
                    color: 'white',
                    lineHeight: 10
                },
                b: {
                    backgroundColor: {
                        image: 'xxx/xxx.jpg'
                    },
                    height: 40
                },
                x: {
                    fontSize: 18,
                    fontFamily: 'Microsoft YaHei',
                    borderColor: '#449933',
                    borderRadius: 4
                }
            }
        }]
    };
    myChart.setOption(option);
}
function comm5() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function comm6() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function comm7() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function comm8() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}