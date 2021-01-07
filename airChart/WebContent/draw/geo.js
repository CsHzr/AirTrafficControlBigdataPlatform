

function geo1() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    myChart.showLoading();
    
    myChart.hideLoading();

    echarts.registerMap('HK', window.jsonData);

    myChart.setOption(option = {
        title: {
            text: '香港18区人口密度 （2011）',
            subtext: '人口密度数据来自Wikipedia',
            sublink: 'http://zh.wikipedia.org/wiki/%E9%A6%99%E6%B8%AF%E8%A1%8C%E6%94%BF%E5%8D%80%E5%8A%83#cite_note-12'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>{c} (p / km2)'
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        visualMap: {
            min: 800,
            max: 50000,
            text: ['High', 'Low'],
            realtime: false,
            calculable: true,
            inRange: {
                color: ['lightskyblue', 'yellow', 'orangered']
            }
        },
        series: [
            {
                name: '香港18区人口密度',
                type: 'map',
                mapType: 'HK', // 自定义扩展图表类型
                label: {
                    show: true
                },
                data: [
                    {name: '中西区', value: 20057.34},
                    {name: '湾仔区', value: 15477.48},
                    {name: '东区', value: 31686.1},
                    {name: '南区', value: 6992.6},
                    {name: '油尖旺区', value: 44045.49},
                    {name: '深水埗区', value: 40689.64},
                    {name: '九龙城区', value: 37659.78},
                    {name: '黄大仙区', value: 45180.97},
                    {name: '观塘区', value: 55204.26},
                    {name: '葵青区', value: 21900.9},
                    {name: '荃湾区', value: 4918.26},
                    {name: '屯门区', value: 5881.84},
                    {name: '元朗区', value: 4178.01},
                    {name: '北区', value: 2227.92},
                    {name: '大埔区', value: 2180.98},
                    {name: '沙田区', value: 9172.94},
                    {name: '西贡区', value: 3368},
                    {name: '离岛区', value: 806.98}
                ]
                //自定义名称映射
                // nameMap: {
                //     'Central and Western': '中西区',
                //     'Eastern': '东区',
                //     'Islands': '离岛',
                //     'Kowloon City': '九龙城',
                //     'Kwai Tsing': '葵青',
                //     'Kwun Tong': '观塘',
                //     'North': '北区',
                //     'Sai Kung': '西贡',
                //     'Sha Tin': '沙田',
                //     'Sham Shui Po': '深水埗',
                //     'Southern': '南区',
                //     'Tai Po': '大埔',
                //     'Tsuen Wan': '荃湾',
                //     'Tuen Mun': '屯门',
                //     'Wan Chai': '湾仔',
                //     'Wong Tai Sin': '黄大仙',
                //     'Yau Tsim Mong': '油尖旺',
                //     'Yuen Long': '元朗'
                // }
            }
        ]
    });

    myChart.setOption(option);

}
function geo2() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');
    echarts.registerMap('china', window.jsonData);
    
    var days = ['01-20', '01-21', '01-22', '01-23', '01-24', '01-25', '01-26', '01-27', '01-28', '01-29', '01-30', ];

    var province = ['湖北省', '广东省', '浙江省', '湖南省', '河南省', '安徽省', '重庆市', '山东省', '江西省', '四川省', '江苏省', '北京市', '福建省', '上海市', '广西壮族自治区', '河北省', '陕西省', '云南省', '海南省', '黑龙江省', '辽宁省', '山西省', '天津市', '甘肃省', '内蒙古自治区', '新疆维吾尔自治区', '宁夏回族自治区', '吉林省', '贵州省', '青海省', '西藏自治区', '澳门特别行政区', '香港特别行政区', '台湾省'];

    var data = [
        [270, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [375, 26, 5, 1, 1, 0, 5, 1, 2, 2, 0, 10, 0, 9, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [444, 32, 10, 9, 5, 4, 9, 6, 3, 8, 1, 14, 1, 16, 2, 1, 0, 1, 4, 2, 2, 1, 3, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0],
        [549, 53, 43, 24, 9, 15, 27, 9, 7, 15, 9, 26, 5, 20, 13, 2, 3, 2, 8, 4, 4, 1, 5, 2, 1, 2, 2, 3, 3, 0, 0, 1, 2, 0],
        [729, 78, 62, 43, 32, 39, 57, 21, 18, 28, 18, 36, 10, 33, 23, 8, 5, 5, 8, 9, 12, 6, 8, 4, 2, 3, 3, 4, 4, 0, 0, 2, 4, 2],
        [1052, 98, 104, 69, 83, 60, 75, 39, 36, 44, 31, 51, 18, 40, 33, 13, 15, 11, 19, 15, 17, 9, 10, 7, 7, 4, 4, 4, 5, 1, 0, 3, 5, 4],
        [1423, 146, 128, 100, 128, 70, 110, 63, 48, 69, 47, 68, 29, 53, 46, 18, 22, 16, 22, 21, 22, 13, 14, 14, 11, 5, 7, 6, 7, 4, 0, 4, 6, 6],
        [2714, 188, 173, 143, 168, 106, 132, 87, 72, 90, 70, 80, 59, 66, 51, 33, 35, 26, 33, 30, 27, 20, 23, 19, 13, 10, 10, 8, 9, 6, 0, 6, 8, 7],
        [3354, 241, 296, 221, 206, 152, 147, 121, 109, 108, 99, 91, 82, 80, 58, 48, 46, 51, 43, 37, 36, 27, 24, 24, 16, 13, 12, 9, 9, 6, 0, 6, 10, 8],
        [4586, 272, 296, 221, 206, 152, 165, 130, 162, 108, 99, 111, 84, 96, 58, 48, 56, 55, 43, 38, 39, 35, 27, 24, 16, 13, 12, 9, 9, 6, 1, 7, 10, 8],
        [5806, 354, 428, 277, 278, 200, 206, 158, 162, 142, 129, 121, 101, 128, 87, 65, 66, 76, 46, 44, 45, 39, 31, 29, 18, 14, 17, 14, 12, 8, 1, 7, 12, 9]
    ];

    var sum = []

    for (var i = 0; i < data.length; i++) {
        sum[i] = 0
        for (var j = 0; j < data[i].length; j++) {

            sum[i] = sum[i] + data[i][j]
        }

    }

    var yqyData = [
        days,
        sum
    ]

    option = {
        baseOption: {
            timeline: {
                axisType: 'category',
                // realtime: false,
                // loop: false,
                autoPlay: true,
                playInterval: 2000,
                symbolSize: 12,
                left: '5%',
                right: '5%',
                bottom: '0%',
                width: '90%',
                // controlStyle: {
                //     position: 'left'
                // },
                data: days,
                tooltip: {
                    formatter: days
                },
            },

            tooltip: {
                show: true,
                formatter: function(params) {
                    return params.name + '：' + params.data['value']
                },
            },
            visualMap: {
                type: 'piecewise',
                pieces: [{
                        min: 1001,
                        color: '#73240D'
                    },
                    {
                        min: 501,
                        max: 1000,
                        color: '#BB0000'
                    },
                    {
                        min: 251,
                        max: 500,
                        color: '#BD430A'
                    },
                    {
                        min: 101,
                        max: 250,
                        color: '#E08150'
                    },
                    {
                        min: 11,
                        max: 100,
                        color: '#E9B090'
                    },
                    {
                        min: 1,
                        max: 10,
                        color: '#F2DDD2'
                    },
                    {
                        value: 0,
                        color: 'white'
                    }
                ],
                orient: 'vertical',
                itemWidth: 25,
                itemHeight: 15,
                showLabel: true,
                seriesIndex: [0],

                textStyle: {
                    color: '#7B93A7'
                },
                bottom: '10%',
                left: "5%",
            },
            grid: [{
                    right: '5%',
                    top: '20%',
                    bottom: '10%',
                    width: '20%'
                },
                {
                    right: '54%',
                    top: '12%',
                    bottom: '75%',
                    width: '40%'
                }

            ],

            xAxis: [{
                min: 0,
                max: 4000,
                show: false


            }, {
                data: yqyData[0],
                gridIndex: 1
            }], //折线图x轴数据赋值，指定坐标信息,
            yAxis: [{
                        inverse: true,
                        offset: '2',
                        'type': 'category',
                        data: '',
                        nameTextStyle: {
                            color: '#fff'
                        },
                        axisTick: {
                            show: false,
                        },
                        axisLabel: {
                            //rotate:45,
                            textStyle: {
                                fontSize: 14,
                                color: '#000000',
                            },
                            interval: 0
                        },
                        axisLine: {
                            show: false,
                            lineStyle: {
                                color: '#333'
                            },
                        },
                        splitLine: {
                            show: false,
                            lineStyle: {
                                color: '#333'
                            }
                        },
                    },


                    {
                        name: '人数',

                        inverse: false,
                        offset: '2',
                        'type': 'category',
                        nameTextStyle: {
                            color: '#000'
                        },
                        axisTick: {
                            show: true,
                        },
                        axisLabel: {
                            //rotate:45,
                            textStyle: {
                                fontSize: 14,
                                color: '#000000',
                            },
                            interval: 'auto'
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: '#333'
                            },
                        },
                        splitLine: {
                            show: false,
                            lineStyle: {
                                color: '#333'
                            }
                        },
                        gridIndex: 1
                    }
                ]


                ,
            geo: {
                map: 'china',
                right: '35%',
                left: '5%',
                label: {
                    emphasis: {
                        show: false,
                    }
                },
                itemStyle: {
                    emphasis: {
                        areaColor: '#00FF00'
                    }
                }
            },
            series: [


                {
                    name: 'mapSer',
                    type: 'map',
                    map: 'china',
                    roam: false,
                    geoIndex: 0,
                    label: {
                        show: false,
                    },
                },
                {
                    'name': '',
                    'type': 'bar',
                    zlevel: 2,
                    barWidth: '40%',
                    label: {
                        normal: {
                            show: true,
                            fontSize: 14,
                            position: 'right',
                            formatter: '{c}'
                        }
                    },
                },

                {
                    name: '确诊新增曲线',
                    type: 'line',
                    xAxisIndex: 1, //指定折线图数据显示到：grid坐标系：0
                    yAxisIndex: 1,
                    data: yqyData[1] //折线图y轴数据赋值
                }

            ],

        },
        animationDurationUpdate: 3000,
        animationEasingUpdate: 'quinticInOut',
        options: []
    };
    for (var n = 0; n < days.length; n++) {

        var res = [];
        for (j = 0; j < data[n].length; j++) {
            res.push({
                name: province[j],
                value: data[n][j]
            });
        }
        res.sort(function(a, b) {
            return b.value - a.value;
        }).slice(0, 6);

        res.sort(function(a, b) {
            return a.value - b.value;
        });
        var res1 = [];
        var res2 = [];
        for (t = 0; t < 10; t++) {
            res1[t] = res[res.length - 1 - t].name;
            res2[t] = res[res.length - 1 - t].value;
        }
        console.log(res1);
        console.log("----------------");
        console.log(province);
        option.options.push({
            title: [{
                    text: days[n] + '新型冠状病毒全国确诊感染人数' + sum[n],
                    textStyle: {
                        color: '#2D3E53',
                        fontSize: 28
                    },
                    left: 20,
                    top: 20,
                },
                {
                    show: true,
                    text: '感染人数前十的省份',
                    textStyle: {
                        color: '#2D3E53',
                        fontSize: 18
                    },
                    right: '10%',
                    top: '15%'
                }
            ],
            yAxis: {
                data: res1,

            },
            series: [{
                type: 'map',
                data: res
            }, {
                type: 'bar',
                data: res2,
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [{
                                    colorStops: [{
                                        offset: 0,
                                        color: '#FF0000' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: '#990000' // 100% 处的颜色
                                    }]
                                },
                                {
                                    colorStops: [{
                                        offset: 0,
                                        color: '#00C0FA' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: '#2F95FA' // 100% 处的颜色
                                    }]
                                }
                            ];
                            if (params.dataIndex < 3) {
                                return colorList[0]
                            } else {
                                return colorList[1]
                            }
                        },
                    }
                },
            }]
        });
    }

    myChart.setOption(option);
}
function geo3() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    //如果想要修改，请点击上方克隆，然后在自己的版本上修改，不要在lz的版本上改！！

    // echarts.extendsMap = function(id, opt) {
    //     // 实例


    //     var cityMap = {
    //         "郑州市": zhengzhou,
    //         "开封市": kaifeng,
    //         "洛阳市": luoyang,
    //         "平顶山市": pingdingshan,
    //         "安阳市": anyang,
    //         "鹤壁市": hebi,
    //         "新乡市": xinxiang,
    //         "焦作市": jiaozuo,
    //         "濮阳市": puyang,
    //         "漯河市": luohe,
    //         "三门峡市": sanmenxia,
    //         "南阳市": nanyang,
    //         "商丘市": shangqiu,
    //         "信阳市": xinyang,
    //         "周口市": zhoukou,
    //         "许昌市": xuchang,
    //         "驻马店市": zhumadian
    //     };

    // }
    var geoGpsMap = {
        '1': [127.9688, 45.368],
        '2': [116.4551, 40.2539],
        '3': [109.1162, 34.2004],
        '4': [113.12244, 23.009505],
        '5': [87.9236, 43.5883],
        '6': [91.11, 29.97],
    };
    var geoCoordMap = {
        '台湾省': [121.5135,25.0308],
        '黑龙江省': [127.9688, 45.368],
        '内蒙古自治区': [110.3467, 41.4899],
        "吉林省": [125.8154, 44.2584],
        '北京市': [116.4551, 40.2539],
        "辽宁省": [123.1238, 42.1216],
        "河北省": [114.4995, 38.1006],
        "天津市": [117.4219, 39.4189],
        "山西省": [112.3352, 37.9413],
        "陕西省": [109.1162, 34.2004],
        "甘肃省": [103.5901, 36.3043],
        "宁夏回族自治区": [106.3586, 38.1775],
        "青海省": [101.4038, 36.8207],
        "新疆维吾尔自治区": [87.9236, 43.5883],
        "西藏自治区": [91.11, 29.97],
        "四川省": [103.9526, 30.7617],
        "重庆市": [108.384366, 30.439702],
        "山东省": [117.1582, 36.8701],
        "河南省": [113.4668, 34.6234],
        "江苏省": [118.8062, 31.9208],
        "安徽省": [117.29, 32.0581],
        "湖北省": [114.3896, 30.6628],
        "浙江省": [119.5313, 29.8773],
        "福建省": [119.4543, 25.9222],
        "江西省": [116.0046, 28.6633],
        "湖南省": [113.0823, 28.2568],
        "贵州省": [106.6992, 26.7682],
        "云南省": [102.9199, 25.4663],
        "广东省": [113.12244, 23.009505],
        "广西省": [108.479, 23.1152],
        "海南省": [110.3893, 19.8516],
        '上海市': [121.4648, 31.2891],
        
    };

    var colors = [
        ["#1DE9B6", "#F46E36", "#04B9FF", "#5DBD32", "#FFC809", "#FB95D5", "#BDA29A", "#6E7074", "#546570", "#C4CCD3"],
        ["#37A2DA", "#67E0E3", "#32C5E9", "#9FE6B8", "#FFDB5C", "#FF9F7F", "#FB7293", "#E062AE", "#E690D1", "#E7BCF3", "#9D96F5", "#8378EA", "#8378EA"],
        ["#DD6B66", "#759AA0", "#E69D87", "#8DC1A9", "#EA7E53", "#EEDD78", "#73A373", "#73B9BC", "#7289AB", "#91CA8C", "#F49F42"],
    ];
    var colorIndex = 0;
    $(function() {
        // var geoCoordMap = {
        //     '郑州': [113.64964385, 34.7566100641],
        //     '开封': [114.351642118, 34.8018541758],
        //     '洛阳': [112.447524769, 34.6573678177],
        //     '平顶山': [113.300848978, 33.7453014565],
        //     '安阳': [114.351806508, 36.1102667222],
        //     '鹤壁': [114.297769838, 35.7554258742],
        //     '新乡': [113.912690161, 35.3072575577],
        //     '焦作': [113.211835885, 35.234607555],
        //     '濮阳': [115.026627441, 35.7532978882],
        //     '漯河': [114.0460614, 33.5762786885],
        //     '三门峡': [111.181262093, 34.7833199411],
        //     '南阳': [112.542841901, 33.0114195691],
        //     "商丘": [115.641885688, 34.4385886402],
        //     '信阳': [114.085490993, 32.1285823075],
        //     '周口': [114.654101942, 33.6237408181],
        //     '许昌': [113.83531246, 34.0267395887],
        //     '驻马店': [114.049153547, 32.9831581541]
        // };

        var year = ["2014", "2015", "2016", "2017", "2018"];
        var mapData = [
            [],
            [],
            [],
            [],
            [],
            []
        ];

        /*柱子Y名称*/
        var categoryData = [];
        var barData = [];
        for (var key in geoCoordMap) {
            categoryData.push(key);
            mapData[0].push({
                "year": '2014',
                "name": key,
                "value": randomNum(100, 300)
            });
            mapData[1].push({
                "year": '2015',
                "name": key,
                "value": randomNum(100, 300)
            });
            mapData[2].push({
                "year": '2016',
                "name": key,
                "value": randomNum(100, 300)
            });
            mapData[3].push({
                "year": '2017',
                "name": key,
                "value": randomNum(100, 300)
            });
            mapData[4].push({
                "year": '2018',
                "name": key,
                "value": randomNum(100, 300)
            });

        }
        for (var i = 0; i < mapData.length; i++) {
            barData.push([]);
            for (var j = 0; j < mapData[i].length; j++) {
                barData[i].push(mapData[i][j].value)
            }
        }
    

        echarts.registerMap('china', window.jsonData);
        var convertData = function(data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            return res;
        };

        var convertToLineData = function(data, gps) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var dataItem = data[i];
                var fromCoord = geoCoordMap[dataItem.name];
                debugger;
                var toCoord = gps; //郑州
                //  var toCoord = geoGps[Math.random()*3]; 
                if (fromCoord && toCoord) {
                    res.push([{
                        coord: fromCoord,
                        value: dataItem.value
                    }, {
                        coord: toCoord,
                    }]);
                }
            }
            return res;
        };

        optionXyMap01 = {
            timeline: {
                data: year,
                axisType: 'category',
                autoPlay: true,
                playInterval: 3000,
                left: '10%',
                right: '10%',
                bottom: '3%',
                width: '80%',
                //  height: null,
                label: {
                    normal: {
                        textStyle: {
                            color: '#ddd'
                        }
                    },
                    emphasis: {
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                symbolSize: 10,
                lineStyle: {
                    color: '#555'
                },
                checkpointStyle: {
                    borderColor: '#777',
                    borderWidth: 2
                },
                controlStyle: {
                    showNextBtn: true,
                    showPrevBtn: true,
                    normal: {
                        color: '#666',
                        borderColor: '#666'
                    },
                    emphasis: {
                        color: '#aaa',
                        borderColor: '#aaa'
                    }
                },

            },
            baseOption: {
                animation: true,
                animationDuration: 1000,
                animationEasing: 'cubicInOut',
                animationDurationUpdate: 1000,
                animationEasingUpdate: 'cubicInOut',
                grid: {
                    right: '1%',
                    top: '15%',
                    bottom: '10%',
                    width: '20%'
                },
                tooltip: {
                    trigger: 'axis', // hover触发器
                    axisPointer: { // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
                        shadowStyle: {
                            color: 'rgba(150,150,150,0.1)' //hover颜色
                        }
                    }
                },
                geo: {
                    show: true,
                    map: 'china',
                    roam: true,
                    zoom: 1,
                    center: [113.83531246, 34.0267395887],
                    label: {
                        emphasis: {
                            show: false
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderColor: 'rgba(147, 235, 248, 1)',
                            borderWidth: 1,
                            areaColor: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 0.8,
                                colorStops: [{
                                    offset: 0,
                                    color: 'rgba(147, 235, 248, 0)' // 0% 处的颜色
                                }, {
                                    offset: 1,
                                    color: 'rgba(147, 235, 248, .2)' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            },
                            shadowColor: 'rgba(128, 217, 248, 1)',
                            // shadowColor: 'rgba(255, 255, 255, 1)',
                            shadowOffsetX: -2,
                            shadowOffsetY: 2,
                            shadowBlur: 10
                        },
                        emphasis: {
                            areaColor: '#389BB7',
                            borderWidth: 0
                        }
                    }
                },
            },
            options: []

        };
        for (var n = 0; n < year.length; n++) {
            optionXyMap01.options.push({
                backgroundColor: '#051b4a',
                title: [{
                        /* text: '地图',
                         subtext: '内部数据请勿外传',
                         left: 'center',
                         textStyle: {
                             color: '#fff'
                         }*/
                    },
                    {
                        id: 'statistic',
                        text: year[n] + "年数据统计情况",
                        left: '75%',
                        top: '8%',
                        textStyle: {
                            color: '#fff',
                            fontSize: 30
                        }
                    }
                ],
                xAxis: {
                    type: 'value',
                    scale: true,
                    position: 'top',
                    min: 0,
                    boundaryGap: false,
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        margin: 2,
                        textStyle: {
                            color: '#aaa'
                        }
                    },
                },
                yAxis: {
                    type: 'category',
                    //  name: 'TOP 20',
                    nameGap: 16,
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: '#ddd'
                        }
                    },
                    axisTick: {
                        show: false,
                        lineStyle: {
                            color: '#ddd'
                        }
                    },
                    axisLabel: {
                        interval: 0,
                        textStyle: {
                            color: '#ddd'
                        }
                    },
                    data: categoryData
                },
                series: [
                    //未知作用
                    {
                        //文字和标志
                        name: 'light',
                        type: 'scatter',
                        coordinateSystem: 'geo',
                        data: convertData(mapData[n]),
                        symbolSize: function(val) {
                            return val[2] / 10;
                        },
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: colors[colorIndex][n]
                            }
                        }
                    },
                    //地图？
                    {
                        type: 'map',
                        map: 'china',
                        geoIndex: 0,
                        aspectScale: 0.75, //长宽比
                        showLegendSymbol: false, // 存在legend时显示
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: false,
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        roam: true,
                        itemStyle: {
                            normal: {
                                areaColor: '#031525',
                                borderColor: '#FFFFFF',
                            },
                            emphasis: {
                                areaColor: '#2B91B7'
                            }
                        },
                        animation: false,
                        data: mapData
                    },
                    //地图点的动画效果
                    {
                        //  name: 'Top 5',
                        type: 'effectScatter',
                        coordinateSystem: 'geo',
                        data: convertData(mapData[n].sort(function(a, b) {
                            return b.value - a.value;
                        }).slice(0, 20)),
                        symbolSize: function(val) {
                            return val[2] / 10;
                        },
                        showEffectOn: 'render',
                        rippleEffect: {
                            brushType: 'stroke'
                        },
                        hoverAnimation: true,
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: true
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: colors[colorIndex][n],
                                shadowBlur: 10,
                                shadowColor: colors[colorIndex][n]
                            }
                        },
                        zlevel: 1
                    },
                    //地图线的动画效果
                    {
                        type: 'lines',
                        zlevel: 2,
                        effect: {
                            show: true,
                            period: 4, //箭头指向速度，值越小速度越快
                            trailLength: 0.02, //特效尾迹长度[0,1]值越大，尾迹越长重
                            symbol: 'arrow', //箭头图标
                            symbolSize: 3, //图标大小
                        },
                        lineStyle: {
                            normal: {
                                color: colors[colorIndex][n],
                                width: 0.1, //尾迹线条宽度
                                opacity: 0.5, //尾迹线条透明度
                                curveness: .3 //尾迹线条曲直度
                            }
                        },
                        data: convertToLineData(mapData[n], geoGpsMap[Math.ceil(Math.random() * 6)])
                    },
                    //柱状图
                    {
                        zlevel: 1.5,
                        type: 'bar',
                        symbol: 'none',
                        itemStyle: {
                            normal: {
                                color: colors[colorIndex][n]
                            }
                        },
                        data: barData[n]
                    }
                ]
            })
        }
        myChart.setOption(optionXyMap01);
    });



    function randomNum(minNum, maxNum) {
        switch (arguments.length) {
            case 1:
                return parseInt(Math.random() * minNum + 1, 10);
                break;
            case 2:
                return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
                break;
            default:
                return 0;
                break;
        }
    }
    //myChart.setOption(option);
}
function geo4() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    /**
    此版本通过设置geoindex && seriesIndex: [1] 属性来实现geo和map共存，来达到hover散点和区域显示tooltip的效果

    默认情况下，map series 会自己生成内部专用的 geo 组件。但是也可以用这个 geoIndex 指定一个 geo 组件。这样的话，map 和 其他 series（例如散点图）就可以共享一个 geo 组件了。并且，geo 组件的颜色也可以被这个 map series 控制，从而用 visualMap 来更改。
    当设定了 geoIndex 后，series-map.map 属性，以及 series-map.itemStyle 等样式配置不再起作用，而是采用 geo 中的相应属性。

    http://echarts.baidu.com/option.html#series-map.geoIndex

    并且加了pin气泡图标以示数值大小
    */


    myChart.showLoading();

    echarts.registerMap('china', window.jsonData);
    myChart.hideLoading();
    var geoCoordMap = {
        '台湾省': [121.5135,25.0308],
        '黑龙江省': [127.9688, 45.368],
        '内蒙古自治区': [110.3467, 41.4899],
        "吉林省": [125.8154, 44.2584],
        '北京市': [116.4551, 40.2539],
        "辽宁省": [123.1238, 42.1216],
        "河北省": [114.4995, 38.1006],
        "天津市": [117.4219, 39.4189],
        "山西省": [112.3352, 37.9413],
        "陕西省": [109.1162, 34.2004],
        "甘肃省": [103.5901, 36.3043],
        "宁夏回族自治区": [106.3586, 38.1775],
        "青海省": [101.4038, 36.8207],
        "新疆维吾尔自治区": [87.9236, 43.5883],
        "西藏自治区": [91.11, 29.97],
        "四川省": [103.9526, 30.7617],
        "重庆市": [108.384366, 30.439702],
        "山东省": [117.1582, 36.8701],
        "河南省": [113.4668, 34.6234],
        "江苏省": [118.8062, 31.9208],
        "安徽省": [117.29, 32.0581],
        "湖北省": [114.3896, 30.6628],
        "浙江省": [119.5313, 29.8773],
        "福建省": [119.4543, 25.9222],
        "江西省": [116.0046, 28.6633],
        "湖南省": [113.0823, 28.2568],
        "贵州省": [106.6992, 26.7682],
        "云南省": [102.9199, 25.4663],
        "广东省": [113.12244, 23.009505],
        "广西省": [108.479, 23.1152],
        "海南省": [110.3893, 19.8516],
        '上海市': [121.4648, 31.2891],
};
    var data = [
        {name:"北京市",value:199},
        {name:"天津市",value:42},
        {name:"河北省",value:102},
        {name:"山西省",value:81},
        {name:"内蒙古自治区",value:47},
        {name:"辽宁省",value:67},
        {name:"吉林省",value:82},
        {name:"黑龙江省",value:123},
        {name:"上海市",value:24},
        {name:"江苏省",value:92},
        {name:"浙江省",value:114},
        {name:"安徽省",value:109},
        {name:"福建省",value:116},
        {name:"江西省",value:91},
        {name:"山东省",value:119},
        {name:"河南省",value:137},
        {name:"湖北省",value:116},
        {name:"湖南省",value:114},
        {name:"重庆市",value:91},
        {name:"四川省",value:125},
        {name:"贵州省",value:62},
        {name:"云南省",value:83},
        {name:"西藏自治区",value:9},
        {name:"陕西省",value:80},
        {name:"甘肃省",value:56},
        {name:"青海省",value:10},
        {name:"宁夏回族自治区",value:18},
        {name:"新疆维吾尔自治区",value:180},
        {name:"广东省",value:123},
        {name:"广西壮族自治区",value:59},
        {name:"海南省",value:14},
    ];
    var max = 480, min = 9; // todo 
    var maxSize4Pin = 100, minSize4Pin = 20;

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                    });
            }
        }
        return res;
    };



    option = {
        backgroundColor: {
        type: 'linear',
        x: 0,
        y: 0,
        x2: 1,
        y2: 1,
        colorStops: [{
            offset: 0, color: '#0f378f' // 0% 处的颜色
        }, {
            offset: 1, color: '#00091a' // 100% 处的颜色
        }],
        globalCoord: false // 缺省为 false
    },
        title: {
            top:20,
            text: '“会员活跃度” - 山东省',
            subtext: '',
            x: 'center',
            textStyle: {
                color: '#ccc'
            }
        },    

       tooltip: {
            trigger: 'item',
            formatter: function (params) {
              if(typeof(params.value)[2] == "undefined"){
              	return params.name + ' : ' + params.value;
              }else{
              	return params.name + ' : ' + params.value[2];
              }
            }
        },
     /*   legend: {
            orient: 'vertical',
            y: 'bottom',
            x: 'right',
             data:['pm2.5'],
            textStyle: {
                color: '#fff'
            }
        },*/
            legend: {
        orient: 'vertical',
        y: 'bottom',
        x:'right',
        data:['pm2.5'],
        textStyle: {
            color: '#fff'
        }
    }, 
        visualMap: {
            show: false,
            min: 0,
            max: 500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'], // 文本，默认为数值文本
            calculable: true,
            seriesIndex: [1],
            inRange: {

            }
        },
        geo: {
            map: 'china',
            show: true,
            roam: true,
            label: {
				normal: {
					show: false
				},
				emphasis: {
					show: false,
				}
			},
            itemStyle: {
                normal: {
                    areaColor: '#3a7fd5',
                    borderColor: '#0a53e9',//线
                    shadowColor: '#092f8f',//外发光
                    shadowBlur: 20
                },
				 emphasis: {
                    areaColor: '#0a2dae',//悬浮区背景
                }
            }
        },
        series : [
      {
         
            symbolSize: 5,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff'
                }
            },
            name: 'light',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            
        },
         {
            type: 'map',
            map: 'china',
            geoIndex: 0,
            aspectScale: 0.75, //长宽比
            showLegendSymbol: false, // 存在legend时显示
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false,
                    textStyle: {
                        color: '#fff'
                    }
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#031525',
                    borderColor: '#FFFFFF',
                },
                emphasis: {
                    areaColor: '#2B91B7'
                }
            },
            animation: false,
            data: data
        },
        {
            name: 'Top 5',
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: 'pin',
            symbolSize: [50,50],
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize: 9,
                    },
                    formatter (value){
                        return value.data.value[2]
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: '#D8BC37', //标志颜色
                }
            },
            data: convertData(data),
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            zlevel: 1
        },
         
    ]
    };
    
    myChart.setOption(option);
}
function geo5() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');
   
    echarts.registerMap('ls', window.jsonData);
    var geoCoordMap = {
        '治金工业区':[120.643338882,31.9787250105],
        '现代农业示范园区':[120.79881073,31.8820563076],
        '经济技术开发区':[120.553760732,31.83],
        '张家港保税区':[120.43,31.970892279],
        '塘桥镇':[120.665220625,31.8253475975],
        '乐余镇':[120.751388101,31.9358733739],
        '凤凰镇':[120.631224777,31.7692103578],
        '南丰镇':[120.762973474,31.8558316614],
        '大新镇':[120.544713024,31.9722253813],
    }
    var data = [
        {name:'治金工业区', value: 29},
        {name:'现代农业示范园区', value: 23},
        {name:'经济技术开发区', value: 137},
        {name:'张家港保税区', value: 165},
        {name:'塘桥镇', value: 70},
        {name:'乐余镇', value: 48},
        {name:'凤凰镇', value: 63},
        {name:'南丰镇', value: 45},
        {name:'大新镇', value: 20},
    
    ];
    var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
    };
    myChart.setOption(option = {
        
        backgroundColor: '#fff',
        tooltip: {
            formatter : function(e){
                if (typeof(e.value)[2] == "undefined") {
                    return e.name;
                }else{
                    return '企业数<br>'+e.name + ':' + e.value[2] + '家';
                }
            
            }
        },
        geo: {
            map: 'ls',
            show: true,
            label: {
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#C9E6FF',
                    borderColor: '#fff',
                    borderWidth: 2,
                    shadowColor: '#5AB2FE',
                    shadowBlur: 20
                }
            },
        zoom:1.2,
        },
        series: [{
            type: 'map',
            map: 'ls',
            geoIndex: 1,
            aspectScale: 0.75, //长宽比
            zoom:1.2,
            label: {
                emphasis: {
                    show: false,
                    textStyle: {
                        color: '#05C3F9'
                    }
                }
            },
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: 'transparent',
                    borderColor: '#fff',
                    borderWidth: 2
                },
                emphasis: {
                    areaColor: '#C9E6FF',
                    shadowColor: '#5AB2FE',
                    shadowBlur: 20
                }
            },
            data: data,
        },
        {
            name: '企业数',
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: 'circle',
            symbolSize: function (val) {
                var num = val[2] / 2;
                if(num < 20){
                    return 20
                }else{
                    return num;
                }
            },
            label: {
                normal: {
                    show: true,
                    formatter: function(value){
                        return value.value[2]
                    },
                    textStyle: {
                        color: '#fff',
                        fontSize: 12,
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: '#1C3E64', //标志颜色
                }
            },
            zlevel: 6,
            data: convertData(data),
        }
        ]
    });
}
function geo6() {
    echarts.dispose(document.getElementById('main')); 
    var myChart = echarts.init(document.getElementById('main'), 'vintage');
    echarts.registerMap('china', window.jsonData);
    function randomData() {
        return Math.round(Math.random()*100);
    }
    var data = [
    
        {
            name: '齐齐哈尔'
        }, {
            name: '盐城'
        }, {
            name: '青岛'
        }, {
            name: '金昌'
        }, {
            name: '泉州'
        }, {
            name: '拉萨'
        }, {
            name: '上海浦东'
        }, {
            name: '攀枝花'
        }, {
            name: '威海'
        }, {
            name: '承德'
        }, {
            name: '汕尾'
        }, {
            name: '克拉玛依'
        }, {
            name: '重庆市'
        },{
            name: '北京市'
        }
    
    ];
    
    
    var geoCoordMap = {
    
        '齐齐哈尔': [123.97, 47.33],
        '盐城': [120.13, 33.38],
        '青岛': [120.33, 36.07],
        '金昌': [102.188043, 38.520089],
        '泉州': [118.58, 24.93],
        '拉萨': [91.11, 29.97],
        '上海浦东': [121.48, 31.22],
        '攀枝花': [101.718637, 26.582347],
        '威海': [122.1, 37.5],
        '承德': [117.93, 40.97],
        '汕尾': [115.375279, 22.786211],
        '克拉玛依': [84.77, 45.59],
        '重庆市': [108.384366, 30.439702],
        '北京市': [116.4551,40.2539]
    
    };
    
    var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
    
    var dataFrom = '北京市';
    
    
    
    myChart.setOption({
        series: [{
            type: 'map',
            map: 'china'
        }]
    });
    
    
    
    var convertData = function(data) {
        var res = [];
    
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
    
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
    
        return res;
    };
    
    option = {
    
    
        title: {
            text: '中国大区分布图',
            subtext: '中国的八大区分布',
            itemGap: 30,
    
            left: 'center',
            textStyle: {
    
                color: '#1a1b4e',
    
                fontStyle: 'normal',
    
                fontWeight: 'bold',
    
                fontSize: 30
    
            },
    
            subtextStyle: {
    
    
                color: '#58d9df',
    
                fontStyle: 'normal',
    
                fontWeight: 'bold',
    
                fontSize: 16
    
    
            }
        },
    
        tooltip: {
            trigger: 'item'
    
        },
    
        visualMap: {
            min: 0,
            max: 100,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],
            calculable: true,
            inRange: {
                color: ['#ffffff', '#E0DAFF', '#ADBFFF', '#9CB4FF', '#6A9DFF', '#3889FF']
            }
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {
                    readOnly: false
                },
                restore: {},
                saveAsImage: {}
            }
        },
    
    
        geo: {
            map: 'china',
            zoom: 1.2,
            label: {
                normal: {
    
                    show: true,
                    color: '#c1c4c8'
                },
                emphasis: {
                    show: false,
                    color: '#292929'
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#fbfbfb',
                    borderColor: '#b9b4b7'
    
                },
                emphasis: {
                    areaColor: '#05ff09'
                }
            }
        },
        series: [
            
            {
            name: '北京市',
            type: 'lines',
            zlevel: 2,
            symbolSize: 10,
            effect: {
                show: true,
                period: 6,
                symbol: planePath,
                trailLength: 0,
                symbolSize: 15
               
            },
            lineStyle: {
                normal: {
                    color:'#c60fff',
                    width: 2,
                    opacity:0.5,
                    curveness:0.2
                }
            },
            data: data.map(function (dataItem) {
                return {
                    fromName: dataFrom,
                    toName: dataItem.name,
                    coords: [
                        geoCoordMap[dataFrom],
                        geoCoordMap[dataItem.name]
                    ]
                }
            })
        },{
            name: '供需占比',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: 8,
            showEffectOn: 'render',
            rippleEffect: {
                scale: 5,
                brushType: 'stroke'
            },
            
           
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#c60fff',
                    shadowBlur: 20,
                    shadowColor: '#333'
                }
            }
        }, {
            type: 'map',
            mapType: 'china',
            geoIndex: 0,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            data: [{
                name: '北京',
                value: randomData()
            }, {
                name: '天津',
                value: randomData()
            }, {
                name: '上海',
                value: randomData()
            }, {
                name: '重庆',
                value: randomData()
            }, {
                name: '河北',
                value: randomData()
            }, {
                name: '河南',
                value: randomData()
            }, {
                name: '云南',
                value: randomData()
            }, {
                name: '辽宁',
                value: randomData()
            }, {
                name: '黑龙江',
                value: randomData()
            }, {
                name: '湖南',
                value: randomData()
            }, {
                name: '安徽',
                value: randomData()
            }, {
                name: '山东',
                value: randomData()
            }, {
                name: '新疆',
                value: randomData()
            }, {
                name: '江苏',
                value: randomData()
            }, {
                name: '浙江',
                value: randomData()
            }, {
                name: '江西',
                value: randomData()
            }, {
                name: '湖北',
                value: randomData()
            }, {
                name: '广西',
                value: randomData()
            }, {
                name: '甘肃',
                value: randomData()
            }, {
                name: '山西',
                value: randomData()
            }, {
                name: '内蒙古',
                value: randomData()
            }, {
                name: '陕西',
                value: randomData()
            }, {
                name: '吉林',
                value: randomData()
            }, {
                name: '福建',
                value: randomData()
            }, {
                name: '贵州',
                value: randomData()
            }, {
                name: '广东',
                value: randomData()
            }, {
                name: '青海',
                value: randomData()
            }, {
                name: '西藏',
                value: randomData()
            }, {
                name: '四川',
                value: randomData()
            }, {
                name: '宁夏',
                value: randomData()
            }, {
                name: '海南',
                value: randomData()
            }, {
                name: '台湾',
                value: randomData()
            }, {
                name: '香港',
                value: randomData()
            }, {
                name: '澳门',
                value: randomData()
            }]
        }]
    };
    
    
    myChart.setOption(option);
    
    
    myChart.on('mouseover', function(params) {
        var city = params.name;
    
    
        if (city == '广东' || city == '广西' || city == '海南') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "广东"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "广西"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "海南"
            });
    
        }
    
        if (city == '山东' || city == '江苏' || city == '浙江' || city == '安徽' || city == '福建' || city == '上海') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "山东"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "江苏"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "浙江"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "安徽"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "福建"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "上海"
            });
    
        }
    
        if (city == '湖北' || city == '湖南' || city == '河南' || city == '江西') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "湖北"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "湖南"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "河南"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "江西"
            });
    
    
        }
    
        if (city == '北京' || city == '天津' || city == '河北' || city == '山西' || city == '内蒙古') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "北京"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "天津"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "河北"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "山西"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "内蒙古"
            });
    
    
        }
        if (city == '宁夏' || city == '新疆' || city == '青海' || city == '陕西' || city == '甘肃') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "宁夏"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "新疆"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "青海"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "陕西"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "甘肃"
            });
    
    
        }
        if (city == '四川' || city == '云南' || city == '贵州' || city == '西藏' || city == '重庆') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "四川"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "云南"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "贵州"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "西藏"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "重庆"
            });
    
    
        }
        if (city == '辽宁' || city == '吉林' || city == '黑龙江') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "辽宁"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "吉林"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "黑龙江"
            });
    
    
        }
        if (city == '台湾' || city == '香港' || city == '澳门') {
    
            myChart.dispatchAction({
                type: 'highlight',
                name: "台湾"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "香港"
            });
            myChart.dispatchAction({
                type: 'highlight',
                name: "澳门"
            });
    
    
        }
    
    
    
    });
    
    myChart.on('mouseout', function(params) {
        var city = params.name;
    
    
        if (city == '广东' || city == '广西' || city == '海南') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "广东"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "广西"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "海南"
            });
    
        }
        if (city == '山东' || city == '江苏' || city == '浙江' || city == '安徽' || city == '福建' || city == '上海') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "山东"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "江苏"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "浙江"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "安徽"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "福建"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "上海"
            });
    
        }
        if (city == '湖北' || city == '湖南' || city == '河南' || city == '江西') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "湖北"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "湖南"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "河南"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "江西"
            });
    
        }
        if (city == '北京' || city == '天津' || city == '河北' || city == '山西' || city == '内蒙古') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "北京"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "天津"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "河北"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "山西"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "内蒙古"
            });
    
        }
        if (city == '宁夏' || city == '新疆' || city == '青海' || city == '陕西' || city == '甘肃') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "宁夏"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "新疆"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "青海"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "陕西"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "甘肃"
            });
    
        }
        if (city == '四川' || city == '云南' || city == '贵州' || city == '西藏' || city == '重庆') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "四川"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "云南"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "贵州"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "西藏"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "重庆"
            });
    
        }
        if (city == '辽宁' || city == '黑龙江' || city == '吉林') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "辽宁"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "黑龙江"
            });
            myChart.dispatchAction({
                type: 'downplay',
                name: "吉林"
            });
    
        }
        if (city == '台湾' || city == '香港' || city == '澳门') {
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "台湾"
            });
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "香港"
            });
    
    
            myChart.dispatchAction({
                type: 'downplay',
                name: "澳门"
            });
    
        }
    
    });
    
    myChart.setOption(option);
}