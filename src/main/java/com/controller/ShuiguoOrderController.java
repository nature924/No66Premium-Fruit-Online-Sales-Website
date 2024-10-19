
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 水果订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shuiguoOrder")
public class ShuiguoOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ShuiguoOrderController.class);

    @Autowired
    private ShuiguoOrderService shuiguoOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShuiguoService shuiguoService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private ShuiguoCommentbackService shuiguoCommentbackService;
@Autowired
private ShangjiaService shangjiaService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("商家".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shuiguoOrderService.queryPage(params);

        //字典表数据转换
        List<ShuiguoOrderView> list =(List<ShuiguoOrderView>)page.getList();
        for(ShuiguoOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShuiguoOrderEntity shuiguoOrder = shuiguoOrderService.selectById(id);
        if(shuiguoOrder !=null){
            //entity转view
            ShuiguoOrderView view = new ShuiguoOrderView();
            BeanUtils.copyProperties( shuiguoOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(shuiguoOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                ShuiguoEntity shuiguo = shuiguoService.selectById(shuiguoOrder.getShuiguoId());
                if(shuiguo != null){
                    BeanUtils.copyProperties( shuiguo , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShuiguoId(shuiguo.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(shuiguoOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShuiguoOrderEntity shuiguoOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shuiguoOrder:{}",this.getClass().getName(),shuiguoOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shuiguoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        shuiguoOrder.setInsertTime(new Date());
        shuiguoOrder.setCreateTime(new Date());
        shuiguoOrderService.insert(shuiguoOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShuiguoOrderEntity shuiguoOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shuiguoOrder:{}",this.getClass().getName(),shuiguoOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shuiguoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShuiguoOrderEntity> queryWrapper = new EntityWrapper<ShuiguoOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShuiguoOrderEntity shuiguoOrderEntity = shuiguoOrderService.selectOne(queryWrapper);
        if(shuiguoOrderEntity==null){
            shuiguoOrderService.updateById(shuiguoOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shuiguoOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ShuiguoOrderEntity> shuiguoOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShuiguoOrderEntity shuiguoOrderEntity = new ShuiguoOrderEntity();
//                            shuiguoOrderEntity.setShuiguoOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            shuiguoOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            shuiguoOrderEntity.setShuiguoId(Integer.valueOf(data.get(0)));   //水果 要改的
//                            shuiguoOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shuiguoOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            shuiguoOrderEntity.setShuiguoOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            shuiguoOrderEntity.setShuiguoOrderCourierName(data.get(0));                    //快递公司 要改的
//                            shuiguoOrderEntity.setShuiguoOrderCourierNumber(data.get(0));                    //订单快递单号 要改的
//                            shuiguoOrderEntity.setShuiguoOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            shuiguoOrderEntity.setShuiguoOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            shuiguoOrderEntity.setInsertTime(date);//时间
//                            shuiguoOrderEntity.setCreateTime(date);//时间
                            shuiguoOrderList.add(shuiguoOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("shuiguoOrderUuidNumber")){
                                    List<String> shuiguoOrderUuidNumber = seachFields.get("shuiguoOrderUuidNumber");
                                    shuiguoOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shuiguoOrderUuidNumber = new ArrayList<>();
                                    shuiguoOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shuiguoOrderUuidNumber",shuiguoOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<ShuiguoOrderEntity> shuiguoOrderEntities_shuiguoOrderUuidNumber = shuiguoOrderService.selectList(new EntityWrapper<ShuiguoOrderEntity>().in("shuiguo_order_uuid_number", seachFields.get("shuiguoOrderUuidNumber")));
                        if(shuiguoOrderEntities_shuiguoOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShuiguoOrderEntity s:shuiguoOrderEntities_shuiguoOrderUuidNumber){
                                repeatFields.add(s.getShuiguoOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shuiguoOrderService.insertBatch(shuiguoOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = shuiguoOrderService.queryPage(params);

        //字典表数据转换
        List<ShuiguoOrderView> list =(List<ShuiguoOrderView>)page.getList();
        for(ShuiguoOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShuiguoOrderEntity shuiguoOrder = shuiguoOrderService.selectById(id);
            if(shuiguoOrder !=null){


                //entity转view
                ShuiguoOrderView view = new ShuiguoOrderView();
                BeanUtils.copyProperties( shuiguoOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(shuiguoOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    ShuiguoEntity shuiguo = shuiguoService.selectById(shuiguoOrder.getShuiguoId());
                if(shuiguo != null){
                    BeanUtils.copyProperties( shuiguo , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShuiguoId(shuiguo.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shuiguoOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ShuiguoOrderEntity shuiguoOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shuiguoOrder:{}",this.getClass().getName(),shuiguoOrder.toString());
            ShuiguoEntity shuiguoEntity = shuiguoService.selectById(shuiguoOrder.getShuiguoId());
            if(shuiguoEntity == null){
                return R.error(511,"查不到该水果");
            }
            // Double shuiguoNewMoney = shuiguoEntity.getShuiguoNewMoney();

            if(false){
            }
            else if((shuiguoEntity.getShuiguoKucunNumber() -shuiguoOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(shuiguoEntity.getShuiguoNewMoney() == null){
                return R.error(511,"水果价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - shuiguoEntity.getShuiguoNewMoney()*shuiguoOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            shuiguoOrder.setShuiguoOrderTypes(1); //设置订单状态为已支付
            shuiguoOrder.setShuiguoOrderTruePrice(shuiguoEntity.getShuiguoNewMoney()*shuiguoOrder.getBuyNumber()); //设置实付价格
            shuiguoOrder.setYonghuId(userId); //设置订单支付人id
            shuiguoOrder.setShuiguoOrderUuidNumber(String.valueOf(new Date().getTime()));
            shuiguoOrder.setShuiguoOrderPaymentTypes(1);
            shuiguoOrder.setInsertTime(new Date());
            shuiguoOrder.setCreateTime(new Date());
                shuiguoEntity.setShuiguoKucunNumber( shuiguoEntity.getShuiguoKucunNumber() -shuiguoOrder.getBuyNumber());
                shuiguoService.updateById(shuiguoEntity);
                shuiguoOrderService.insert(shuiguoOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String shuiguoOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer shuiguoOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("shuiguoOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("shuiguos"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> shuiguos = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<ShuiguoOrderEntity> shuiguoOrderList = new ArrayList<>();
        //商品表
        List<ShuiguoEntity> shuiguoList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : shuiguos) {
           //取值
            Integer shuiguoId = Integer.valueOf(String.valueOf(map.get("shuiguoId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            ShuiguoEntity shuiguoEntity = shuiguoService.selectById(shuiguoId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(shuiguoEntity.getShuiguoKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(shuiguoEntity.getShuiguoName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                shuiguoEntity.setShuiguoKucunNumber(shuiguoEntity.getShuiguoKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            ShuiguoOrderEntity shuiguoOrderEntity = new ShuiguoOrderEntity<>();

            //赋值订单信息
            shuiguoOrderEntity.setShuiguoOrderUuidNumber(shuiguoOrderUuidNumber);//订单号
            shuiguoOrderEntity.setAddressId(addressId);//收货地址
            shuiguoOrderEntity.setShuiguoId(shuiguoId);//水果
            shuiguoOrderEntity.setYonghuId(userId);//用户
            shuiguoOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            shuiguoOrderEntity.setShuiguoOrderTypes(1);//订单类型
            shuiguoOrderEntity.setShuiguoOrderPaymentTypes(shuiguoOrderPaymentTypes);//支付类型
            shuiguoOrderEntity.setInsertTime(new Date());//订单创建时间
            shuiguoOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(shuiguoOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(shuiguoEntity.getShuiguoNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额


                    shuiguoOrderEntity.setShuiguoOrderTruePrice(money);

                }
            }
            shuiguoOrderList.add(shuiguoOrderEntity);
            shuiguoList.add(shuiguoEntity);

        }
        shuiguoOrderService.insertBatch(shuiguoOrderList);
        shuiguoService.updateBatchById(shuiguoList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ShuiguoOrderEntity shuiguoOrder = shuiguoOrderService.selectById(id);
            Integer buyNumber = shuiguoOrder.getBuyNumber();
            Integer shuiguoOrderPaymentTypes = shuiguoOrder.getShuiguoOrderPaymentTypes();
            Integer shuiguoId = shuiguoOrder.getShuiguoId();
            if(shuiguoId == null)
                return R.error(511,"查不到该水果");
            ShuiguoEntity shuiguoEntity = shuiguoService.selectById(shuiguoId);
            if(shuiguoEntity == null)
                return R.error(511,"查不到该水果");
            Double shuiguoNewMoney = shuiguoEntity.getShuiguoNewMoney();
            if(shuiguoNewMoney == null)
                return R.error(511,"水果价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(shuiguoOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = shuiguoEntity.getShuiguoNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }

            shuiguoEntity.setShuiguoKucunNumber(shuiguoEntity.getShuiguoKucunNumber() + buyNumber);



            shuiguoOrder.setShuiguoOrderTypes(2);//设置订单状态为退款
            shuiguoOrderService.updateById(shuiguoOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            shuiguoService.updateById(shuiguoEntity);//更新订单中水果的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String shuiguoOrderCourierNumber, String shuiguoOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ShuiguoOrderEntity  shuiguoOrderEntity = new  ShuiguoOrderEntity();;
        shuiguoOrderEntity.setId(id);
        shuiguoOrderEntity.setShuiguoOrderTypes(3);
        shuiguoOrderEntity.setShuiguoOrderCourierNumber(shuiguoOrderCourierNumber);
        shuiguoOrderEntity.setShuiguoOrderCourierName(shuiguoOrderCourierName);
        boolean b =  shuiguoOrderService.updateById( shuiguoOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ShuiguoOrderEntity  shuiguoOrderEntity = new  ShuiguoOrderEntity();
        shuiguoOrderEntity.setId(id);
        shuiguoOrderEntity.setShuiguoOrderTypes(4);
        boolean b =  shuiguoOrderService.updateById( shuiguoOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer shuiguoCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            ShuiguoOrderEntity shuiguoOrder = shuiguoOrderService.selectById(id);
        if(shuiguoOrder == null)
            return R.error(511,"查不到该订单");
        if(shuiguoOrder.getShuiguoOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer shuiguoId = shuiguoOrder.getShuiguoId();
        if(shuiguoId == null)
            return R.error(511,"查不到该水果");

        ShuiguoCommentbackEntity shuiguoCommentbackEntity = new ShuiguoCommentbackEntity();
            shuiguoCommentbackEntity.setId(id);
            shuiguoCommentbackEntity.setShuiguoId(shuiguoId);
            shuiguoCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            shuiguoCommentbackEntity.setShuiguoCommentbackText(commentbackText);
            shuiguoCommentbackEntity.setInsertTime(new Date());
            shuiguoCommentbackEntity.setReplyText(null);
            shuiguoCommentbackEntity.setUpdateTime(null);
            shuiguoCommentbackEntity.setCreateTime(new Date());
            shuiguoCommentbackService.insert(shuiguoCommentbackEntity);

            shuiguoOrder.setShuiguoOrderTypes(5);//设置订单状态为已评价
            shuiguoOrderService.updateById(shuiguoOrder);//根据id更新
            return R.ok();
    }












}
