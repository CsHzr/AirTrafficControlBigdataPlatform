function pie1() {
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
            data: ['直达', '营销广告', '搜索引擎', '邮件营销', '联盟广告', '视频广告', '百度', '谷歌', '必应', '其他']
        },
        series: [
            {
                name: '访问来源',
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
                    {value: 335, name: '直达', selected: true},
                    {value: 679, name: '营销广告'},
                    {value: 1548, name: '搜索引擎'}
                ]
            },
            {
                name: '访问来源',
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
                    {value: 335, name: '直达'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1048, name: '百度'},
                    {value: 251, name: '谷歌'},
                    {value: 147, name: '必应'},
                    {value: 102, name: '其他'}
                ]
            }
        ]
    };
    myChart.setOption(option);
}

function pie2() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');
    option = {
        title: {
            text: '南丁格尔玫瑰图',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            left: 'center',
            top: 'bottom',
            data: ['rose1', 'rose2', 'rose3', 'rose4', 'rose5', 'rose6', 'rose7', 'rose8']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '半径模式',
                type: 'pie',
                radius: [20, 110],
                center: ['25%', '50%'],
                roseType: 'radius',
                label: {
                    show: false
                },
                emphasis: {
                    label: {
                        show: true
                    }
                },
                data: [
                    {value: 10, name: 'rose1'},
                    {value: 5, name: 'rose2'},
                    {value: 15, name: 'rose3'},
                    {value: 25, name: 'rose4'},
                    {value: 20, name: 'rose5'},
                    {value: 35, name: 'rose6'},
                    {value: 30, name: 'rose7'},
                    {value: 40, name: 'rose8'}
                ]
            },
            {
                name: '面积模式',
                type: 'pie',
                radius: [30, 110],
                center: ['75%', '50%'],
                roseType: 'area',
                data: [
                    {value: 10, name: 'rose1'},
                    {value: 5, name: 'rose2'},
                    {value: 15, name: 'rose3'},
                    {value: 25, name: 'rose4'},
                    {value: 20, name: 'rose5'},
                    {value: 35, name: 'rose6'},
                    {value: 30, name: 'rose7'},
                    {value: 40, name: 'rose8'}
                ]
            }
        ]
    };
    
    myChart.setOption(option);
}
function pie3() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function pie4() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function pie5() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function pie6() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}
function pie7() {
    echarts.dispose(document.getElementById('main')); 
    
    var myChart = echarts.init(document.getElementById('main'), 'vintage');

    
    myChart.setOption(option);
}