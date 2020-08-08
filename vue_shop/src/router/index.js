import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../pages/login/Login'
import Public from '../pages/public/Public'
import ShowSpu from '../pages/public/ShowSpu'
import Parent from '../components/Parent'
import Child from '../components/Child'
import Register from '../pages/login/Register'
import Forget from '../pages/login/Forget'
import MerchantHome from '../pages/merchant/home/Index'
import Welcome from '../pages/merchant/home/Welcome'
import GoodsList from '../pages/merchant/goods/List'
import AddGood from '../pages/merchant/goods/Add'
// import Order from '../pages/merchant/order/Order'
import Orders from '../pages/merchant/order/Orders'
import Send from '../pages/merchant/order/Send'
import DataEchart from '../pages/merchant/dataAnalysis/DataEchart'
import MerchantTest from '../components/merchant/MerchantTest'
import ShowUser from '../pages/admin/components/ShowUser'
import SPUDetails from '../pages/admin/components/SPUDetails'
import EchartsTest from '../pages/login/EchartsTest'
import BuyerHome from '../pages/buyer/BuyerHome'
import Information from '../pages/buyer/Information'
import UserInfo from '../pages/buyer/UserInfo'
import MyOrder from '../pages/buyer/MyOrder'
import AdminInfo from '../pages/admin/components/AdminInfo'
import MyCollection from '../pages/buyer/MyCollection'
import ShoppingCart from '../pages/buyer/ShoppingCart'
import AdminLogin from '../pages/admin/login/AdminLogin'
import AdminManage from '../pages/admin/manage/AdminManage'
import SPUmanage from '../pages/admin/components/SPUmanage'
import UserManage from '../pages/admin/components/UserManage'
import CityEcharts from '../pages/admin/components/CityEcharts'

import CreatOrder from '../pages/buyer/CreatOrder'

import Echarts from '../pages/login/Echarts'

Vue.use(VueRouter)

const routes = [
  // 初始页面为public
  {
    path: '/',
    redirect: '/public'
  },
  {
    path: '/public',
    component: Public
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/forget',
    component: Forget
  },
  {
    path: '/showSpu',
    component: ShowSpu
  },
  // 父子组件测试用
  {
    path: '/parent',
    component: Parent
  },
  {
    path: '/child',
    component: Child
  },
  // {
  //   path: '/test',
  //   component: EchartsTest
  // },
  {
    path: '/test',
    component: EchartsTest
  },
  // 商户工作台
  {
    path: '/merchanthome',
    component: MerchantHome,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/goods', component: GoodsList },
      { path: '/add', component: AddGood },
      { path: '/orders', component: Orders },
      { path: '/send', component: Send },
      { path: '/data', component: DataEchart }
    ]
  },
  {
    path: '/merchantTest',
    component: MerchantTest
  },
  {
    path: '/buyerhome',
    component: BuyerHome,
    children: [{
      path: '/information',
      component: Information
    },
    {
      path: '/userinfo',
      component: UserInfo
    },
    {
      path: '/shoppingcart',
      component: ShoppingCart
    },
    {
      path: '/myorder',
      component: MyOrder
    },
    {
      path: '/mycollection',
      component: MyCollection
    }]
  },
  {
    path: '/adminlogin',
    component: AdminLogin
  },
  {
    path: '/creatOrder',
    component: CreatOrder
  },
  {
    path: '/cityEcharts',
    component: CityEcharts
  },
  {

    path: '/cityEcharts',
    component: CityEcharts
  },
  {

    path: '/showUser',
    component: ShowUser
  },
  {
    path: '/SPUDetails',
    component: SPUDetails
  },
  {
    path: '/adminmanage',
    component: AdminManage,
    children: [{
      path: '/spumanage',
      component: SPUmanage
    },
    {
      path: '/admininfo',
      component: AdminInfo
    },
    {
      path: '/usermanage',
      component: UserManage
    },
    {
      path: '/cityEcharts',
      component: CityEcharts
    }]
  },
  {
    path: '/echarts',
    component: Echarts
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
