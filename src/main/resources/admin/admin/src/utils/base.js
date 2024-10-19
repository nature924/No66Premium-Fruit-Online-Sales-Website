const base = {
    get() {
        return {
            url : "http://localhost:8080/shuiguozaixianxiaoshou/",
            name: "shuiguozaixianxiaoshou",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/shuiguozaixianxiaoshou/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "精品水果线上销售网站"
        } 
    }
}
export default base
