function delFruit(fid) {
    if(confirm('是否确认删除')) {
        window.location.href='fruit.do?fid='+fid+'&operate=del';
    }
}

function page(pageNum) {
    window.location.href="fruit.do?pageNum="+pageNum;
}