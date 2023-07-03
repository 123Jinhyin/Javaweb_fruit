function $(id){
    return document.getElementById(id);
}

window.onload = function (){
    //当页面加载完成，我们需要绑定各种事件
    //根据id获取表格
    var fruitTbl = $("tbl_fruit");
    //获取表格中的所有行
    var rows = fruitTbl.rows;
    for(var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        trBindEvent(tr);
    }
    $("addBtn").onclick=addFruit;
}

function  trBindEvent(tr) {
    //1.绑定鼠标悬浮设置背景颜色事件
    tr.onmouseover = showBGColor;
    tr.onmouseout = clearBGColor;
    //2.绑定鼠标在单价单元格变手势的事件
    var cells = tr.cells;
    var priceTD = cells[1];
    priceTD.onmouseover = showHand;
    //3.绑定鼠标点击单价单元格的事件
    priceTD.onclick = editPrice;
    //4.绑定删除的点击事件
    var deleteTD = cells[4];
    deleteTD.onclick = delFruit;
}

//添加水果信息
function addFruit(event) {
    var name = $("f_name").value;
    var price = parseInt($("f_price").value);
    var count = parseInt($("f_count").value);
    var xj = price * count;

    var fruitTbl = $("tbl_fruit");
    var tr = fruitTbl.insertRow(fruitTbl.rows.length - 1);
    var nameTD = tr.insertCell();
    nameTD.innerText = name;

    var priceTD = tr.insertCell();
    priceTD.innerText = price.toString();

    var countTD = tr.insertCell();
    countTD.innerText = count.toString();

    var xjTD = tr.insertCell();
    xjTD.innerText = xj.toString();

    var editTD =tr.insertCell();
    editTD.innerText = "删除";

    updateZJ();

    trBindEvent(tr);
}

function delFruit(event) {
    if(event && event.target && event.target.tagName == "TD") {
        if(window.confirm("是否删除当前库存记录")) {
            var deleteTD = event.target;
            var tr = deleteTD.parentElement;
            var fruitTbl = $("tbl_fruit");
            fruitTbl.deleteRow(tr.rowIndex);
            updateZJ();
        }
    }
}

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


//当鼠标点击单价单元格时，进行价格编辑
function editPrice(event) {
    if(event && event.target && event.target.tagName == "TD") {
        var priceTD = event.target;
        if(priceTD.firstChild && priceTD.firstChild.nodeType == 3){
            //innerText 表示设置或者获取当前节点的内部节点
            var oldPrice = priceTD.innerText;
            //innerHTML 表示设置当前节点的内部HTML
            priceTD.innerHTML = "<input type = 'text' size='4'/>";
            var input = priceTD.firstChild;
            if(input.tagName == "INPUT" ) {
                input.value = oldPrice;
                //选中输入框内部的文本
                input.select();
                //1.绑定输入框失去焦点事件，失去焦点，更新单价
                input.onblur = function(event) {
                    if (input.value === '') {
                        input.value = oldPrice; // 将输入框的值恢复为原来的值
                    }
                    updatePrice(event); // 调用 updatePrice 函数
                };

                //2.在输入框上绑定键盘摁下的事件，此处我需要保证用户输入的是数字
                input.onkeydown = ckInput;
            }
        }
    }
}

function ckInput(event) {
    var kc = event.keyCode;
    console.log(kc);
    //0 ~ 9 : 48~57
    //backspace : 8
    //enter : 13
    //console.log(kc)
    if(!(( kc >= 48 && kc <= 57 ) || kc == 8 || kc == 13 )) {
        event.returnValue = false;
    }
    if(kc == 13) {
        event.target.blur();
    }
}

function updatePrice(event) {
    if(event && event.target && event.target.tagName == "INPUT") {
        var input = event.target;
        var newPrice = input.value;
        var priceTD = input.parentElement;
        priceTD.innerText = newPrice;
        //更新当前行的小计这一个格子的值
        updateXJ(priceTD.parentElement);
    }
}

//更新指定行的小计
function updateXJ(tr) {
    if(tr && tr.tagName == "TR") {
        var tds = tr.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;

        var xj = parseInt(price) * parseInt(count);
        tds[3].innerText = xj;

        //更新总计
        updateZJ();
    }
}

//更新总计
function updateZJ() {
    var fruitTal = $("tbl_fruit");
    var rows = fruitTal.rows;
    var sum = 0;
    for(var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        var xj = parseInt(tr.cells[3].innerText);
        sum = sum + xj;
    }
    rows[rows.length - 1].cells[1].innerText = sum;
}