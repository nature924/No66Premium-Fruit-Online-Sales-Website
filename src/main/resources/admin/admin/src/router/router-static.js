import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import address from '@/views/modules/address/list'
    import cart from '@/views/modules/cart/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import shuiguo from '@/views/modules/shuiguo/list'
    import shuiguoCollection from '@/views/modules/shuiguoCollection/list'
    import shuiguoCommentback from '@/views/modules/shuiguoCommentback/list'
    import shuiguoOrder from '@/views/modules/shuiguoOrder/list'
    import singleSeach from '@/views/modules/singleSeach/list'
    import yonghu from '@/views/modules/yonghu/list'
    import shangjia from '@/views/modules/shangjia/list'
    import config from '@/views/modules/config/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryIsdefault from '@/views/modules/dictionaryIsdefault/list'
    import dictionaryJinkou from '@/views/modules/dictionaryJinkou/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangjiaXingji from '@/views/modules/dictionaryShangjiaXingji/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'
    import dictionaryShuiguo from '@/views/modules/dictionaryShuiguo/list'
    import dictionaryShuiguoCollection from '@/views/modules/dictionaryShuiguoCollection/list'
    import dictionaryShuiguoErji from '@/views/modules/dictionaryShuiguoErji/list'
        import dictionaryShuiguoErjiAddOrUpdate from '@/views/modules/dictionaryShuiguoErji/add-or-update'//二级
    import dictionaryShuiguoOrder from '@/views/modules/dictionaryShuiguoOrder/list'
    import dictionaryShuiguoOrderPayment from '@/views/modules/dictionaryShuiguoOrderPayment/list'
    import dictionarySingleSeach from '@/views/modules/dictionarySingleSeach/list'
    import dictionaryTejia from '@/views/modules/dictionaryTejia/list'
    import dictionaryTuijian from '@/views/modules/dictionaryTuijian/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryIsdefault',
        name: '是否默认地址',
        component: dictionaryIsdefault
    }
    ,{
        path: '/dictionaryJinkou',
        name: '是否进口',
        component: dictionaryJinkou
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangjiaXingji',
        name: '商家信用类型',
        component: dictionaryShangjiaXingji
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/dictionaryShuiguo',
        name: '一级分类',
        component: dictionaryShuiguo
    }
    ,{
        path: '/dictionaryShuiguoCollection',
        name: '收藏表类型',
        component: dictionaryShuiguoCollection
    }
    ,{
        path: '/dictionaryShuiguoErji',
        name: '二级分类',
        component: dictionaryShuiguoErji
    }
    ,{
        path: '/dictionaryShuiguoErjiAddOrUpdate',
        name: '二级分类的新增修改页面',
        component: dictionaryShuiguoErjiAddOrUpdate
    }
    ,{
        path: '/dictionaryShuiguoOrder',
        name: '订单类型',
        component: dictionaryShuiguoOrder
    }
    ,{
        path: '/dictionaryShuiguoOrderPayment',
        name: '订单支付类型',
        component: dictionaryShuiguoOrderPayment
    }
    ,{
        path: '/dictionarySingleSeach',
        name: '单页数据类型',
        component: dictionarySingleSeach
    }
    ,{
        path: '/dictionaryTejia',
        name: '是否特价',
        component: dictionaryTejia
    }
    ,{
        path: '/dictionaryTuijian',
        name: '是否推荐',
        component: dictionaryTuijian
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/address',
        name: '收货地址',
        component: address
      }
    ,{
        path: '/cart',
        name: '购物车',
        component: cart
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/shuiguo',
        name: '水果',
        component: shuiguo
      }
    ,{
        path: '/shuiguoCollection',
        name: '水果收藏',
        component: shuiguoCollection
      }
    ,{
        path: '/shuiguoCommentback',
        name: '水果评价',
        component: shuiguoCommentback
      }
    ,{
        path: '/shuiguoOrder',
        name: '水果订单',
        component: shuiguoOrder
      }
    ,{
        path: '/singleSeach',
        name: '单页数据',
        component: singleSeach
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/shangjia',
        name: '商家',
        component: shangjia
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
