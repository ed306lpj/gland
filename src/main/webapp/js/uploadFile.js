let UploadFile = function () {

    return {

        openDilog :function (){
            alert(2)
            debugger;
           jQuery('#myContent').dialog(
                {
                    title:'自定义Title',
                    modal: true,//是否遮挡后面的元素
                    resizable:true, //是否可以改变大小
                    draggable:false,//是否可以拖拽
                    create:function(){
                        //弹出的时候初始化，可以传一些参数进去，也可以绑定弹出框中元素的一些事件
                        alert('初始化');
                    },
                    buttons:[
                        {
                            text:"确定",
                            click:function(){
                                alert('点击确定');
                                $(this).dialog( "close" )
                            }
                        },
                        {
                            text:"取消",
                            click:function(){
                                alert('点击取消');
                                $(this).dialog( "close" )
                            }
                        }
                    ]
                }
            );
        }

    };
}();