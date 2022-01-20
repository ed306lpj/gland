var CommonJs = function () {
	
	let cloumStr = ['用药量','血清游离T3测定','血清游离T4测定','血清促甲状腺激素测定','血清甲状腺球蛋白','甲功5','甲功6','甲功7'];

	let minNum = [0,2.76,10.42,0.35,1.6,0,0,0];
	let maxNum = [20,6.3,24.32,5.5,100,55,100,100];

	let colorClassArr=['bg-info','bg-success','bg-danger','bg-primary','bg-warning','bg-light','bg-dark','bg-info'];

	let hospitalArr=['未知','301医院','其他'];

	let colorArr=['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1']



	let contextPath = window.location.host;
	return {
		initData :function(startDate,endDate){
			jQuery.ajax({
                type: "get",
                url: 'index?startDate='+startDate+'&endDate='+endDate+"t="+new Date().getTime(),
                success: function(result) {
                	let checkData = result['data'];
					let checkDate = checkData['checkDates'];
					let dosages = checkData['dosages'];
					let checkOpt1 = checkData['checkOpt1'];
					let checkOpt2 = checkData['checkOpt2'];
					let checkOpt3 = checkData['checkOpt3'];
					let checkOpt4 = checkData['checkOpt4'];
					let checkOpt5 = checkData['checkOpt5'];
					let checkOpt6 = checkData['checkOpt6'];
					let checkOpt7 = checkData['checkOpt7'];
					let checkOpt8 = checkData['checkOpt8'];
					CommonJs.allData(checkDate,checkOpt1,checkOpt2,checkOpt3,checkOpt4,checkOpt5,checkOpt6,checkOpt7,dosages);

					CommonJs.allDataMore(checkDate,dosages,0);
					CommonJs.allDataMore(checkDate,checkOpt1,1);
					CommonJs.allDataMore(checkDate,checkOpt2,2);
					CommonJs.allDataMore(checkDate,checkOpt3,3);
					CommonJs.allDataMore(checkDate,checkOpt4,4);

					
					CommonJs.initAvgInfo(dosages,0);
					CommonJs.initAvgInfo(checkOpt1,1);
					CommonJs.initAvgInfo(checkOpt2,2);
					CommonJs.initAvgInfo(checkOpt3,3);
					CommonJs.initAvgInfo(checkOpt4,4);
					CommonJs.initAvgInfo(checkOpt5,5);
					CommonJs.initAvgInfo(checkOpt6,6);
					CommonJs.initAvgInfo(checkOpt7,7);
					CommonJs.checkList(checkOpt8,checkDate);
					
                }
            });
		},
		
		initAvgInfo :function(arr,idx){
			let a = arr.length;
			let b = 0;
			for (let i = 0; i < arr.length; i++) {
				b=b+arr[i];
			}

			let agv = 0;
			
			if(a>0){
				agv= (b/a).toFixed(2);
			}


			let xx = (agv*100)/maxNum[idx];
			let percentNum = xx.toFixed(2);
			jQuery("#avgNumTab").append('<div class="col-md-6 col-xl-3"><div class="card stat-widget"><div class="card-body"><h5 class="card-title">'+cloumStr[idx]+'平均值'+minNum[idx]+'~'+maxNum[idx]+'</h5><h2>'+agv+'</h2><p>占比进度条</p><div class="progress"><div class="progress-bar '+colorClassArr[idx]+' progress-bar-striped" role="progressbar" style="width: '+percentNum+'%" aria-valuenow="'+percentNum+'" aria-valuemin="'+minNum[idx]+'" aria-valuemax="'+maxNum[idx]+'"></div></div></div></div></div>');
		},

		checkList :function(arr,checkDate){

			let htmlStr="";
			for (let i = 0; i < arr.length; i++){

				htmlStr+=' <tr><th scope="row"><img src="assets/images/avatars/profile-image.png" alt="">马燕霞</th><td>'+checkDate[i]+'</td><td>'+hospitalArr[arr[i]]+'</td><td>空</td><td><span class="badge bg-info">空</span></td></tr>';
			}
			jQuery("#userTable").html(htmlStr);
		},
		
		
		
		allData :function(checkDate,checkOpt1,checkOpt2,checkOpt3,checkOpt4,checkOpt5,checkOpt6,checkOpt7,dosages){
			$('#container').highcharts({
	            title: {
	                text: '检测数据汇总分析',
	                x: -20 //center
	            },
	            subtitle: {
	                text: '该时间段内所有的数据汇总走向图',
	                x: -20
	            },
	            xAxis: {
	                categories: checkDate
	            },
	            yAxis: {
	                title: {
	                    text: '检测值'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                valueSuffix: ''
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'middle',
	                borderWidth: 0
	            },
	            series: [ {
	                name: cloumStr[0],
	                data: dosages
	            },{
	                name: cloumStr[1],
	                data: checkOpt1
	            }, {
	                name: cloumStr[2],
	                data: checkOpt2
	            }, {
	                name: cloumStr[3],
	                data: checkOpt3
	            }, {
	                name: cloumStr[4],
	                data: checkOpt4
	            }]
	        });
		},





		allDataMore :function(checkDate,arrData,idx){
			$(`#container${idx}`).highcharts({
	            title: {
	                text: '日期对应的'+cloumStr[idx]+'分析',
	                x: -20 //center
	            },
	            subtitle: {
	                text: '该时间段内'+cloumStr[idx]+'的数据汇总走向图',
	                x: -20
	            },
	            xAxis: {
	                categories: checkDate
	            },
	            yAxis: {
	                title: {
	                    text: '检测值'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                valueSuffix: ''
	            },
				plotOptions: {
					line: {
						dataLabels: {
							// 开启数据标签
							enabled: true
						},
						// 关闭鼠标跟踪，对应的提示框、点击事件会失效
						enableMouseTracking: true
					}
				},
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'middle',
	                borderWidth: 0
	            },
	            series: [ {
	                name: cloumStr[idx],
	                data: arrData,
	            }]
	        });
		},
		saveData :function(checkDataResult,domId){
			jQuery.ajax({
                type: 'post',
				dataType:'json',
				contentType: "application/json;",
				data:JSON.stringify(checkDataResult),
                url: 'add',
                success: function(postData,status) {
                	var result = postData['data'];
					if(result){
						jQuery("#alert3-msg").html("保存成功");
						syalert.syhide(domId);	
						syalert.syopen('alert3');
					}else{
						jQuery("#alert3-msg").html("保存失败");
						syalert.syopen('alert3');
					}
                }
	        });
			
		},
		checkDataError : function(domId){
			let checkDate = jQuery("#checkDate").val();
			let dosages = jQuery("#dosage").val();
			let checkOpt1 = jQuery("#checkOpt1").val();
			let checkOpt2 = jQuery("#checkOpt2").val();
			let checkOpt3 = jQuery("#checkOpt3").val();
			let checkOpt4 = jQuery("#checkOpt4").val();
			let checkOpt5 = jQuery("#checkOpt5").val();
			let checkOpt6 = jQuery("#checkOpt6").val();
			let checkOpt7 = jQuery("#checkOpt7").val();

			let flag = true;
			if(checkDate==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkDate").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&dosages==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#dosages").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt1==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt1").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt2==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt2").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt3==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt3").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt4==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt4").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt5==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt5").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt6==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt6").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag&&checkOpt7==""){
				jQuery("#alert3-msg").html("请输入");
				jQuery("#checkOpt7").focus();
				syalert.syopen('alert3');
				flag = false;
			}
			if(flag){
				//			syalert.syhide(id);	
				let data={
					"checkDate":checkDate,
					"dosage":dosages,
					"checkOpt1":checkOpt1,
					"checkOpt2":checkOpt2,
					"checkOpt3":checkOpt3,
					"checkOpt4":checkOpt4,
					"checkOpt5":checkOpt5,
					"checkOpt6":checkOpt6,
					"checkOpt7":checkOpt7
				}
			 	CommonJs.saveData(data,domId);
			}
			

		}
		
	};
}();