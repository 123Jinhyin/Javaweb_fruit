
//当鼠标悬浮时，显示背景颜色
function showBGColor(event) {
    //event: 当前发生的事件
    if(event && event.target && event.target.tagName == "TD"){
        var td = event.target;
        //td.parentElement 表示获取td的父元素 -> TR
        var tr = td.parentElement;
        //如果想要通过js代码设置某节点的样式，则需要加上 .style
        tr.style.backgroundColor = "navy";
    }

    //tr.cells表示获取这个tr中的所有的单元格
    var tds = tr.cells;
    for(var i = 0; i < tds.length; i++){
        tds[i].style.color = "white";
    }
}

//当鼠标离开时，恢复背景颜色
function clearBGColor(event) {
    if(event && event.target && event.target.tagName == "TD") {
        var td = event.target;
        var tr = td.parentElement;
        tr.style.backgroundColor = "transparent"
    }
    var tds = tr.cells;
    for(var i = 0; i < tds.length; i++){
        tds[i].style.color = "black";
    }
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(event) {
    if(event && event.target && event.target.tagName == "TD") {
        var td = event.target;
        //cursor : 光标
        td.style.cursor = "pointer";
    }
}