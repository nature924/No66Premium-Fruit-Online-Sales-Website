
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
 * 水果
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shuiguo")
public class ShuiguoController {
    private static final Logger logger = LoggerFactory.getLogger(ShuiguoController.class);

    @Autowired
    private ShuiguoService shuiguoService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ShangjiaService shangjiaService;

    @Autowired
    private YonghuService yonghuService;


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
        params.put("shuiguoDeleteStart",1);params.put("shuiguoDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shuiguoService.queryPage(params);

        //字典表数据转换
        List<ShuiguoView> list =(List<ShuiguoView>)page.getList();
        for(ShuiguoView c:list){
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
        ShuiguoEntity shuiguo = shuiguoService.selectById(id);
        if(shuiguo !=null){
            //entity转view
            ShuiguoView view = new ShuiguoView();
            BeanUtils.copyProperties( shuiguo , view );//把实体数据重构到view中

                //级联表
                ShangjiaEntity shangjia = shangjiaService.selectById(shuiguo.getShangjiaId());
                if(shangjia != null){
                    BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangjiaId(shangjia.getId());
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
    public R save(@RequestBody ShuiguoEntity shuiguo, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shuiguo:{}",this.getClass().getName(),shuiguo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("商家".equals(role)){
            shuiguo.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            shuiguo.setTuijianTypes(2);
            shuiguo.setShangxiaTypes(2);
        }else{
            shuiguo.setShangxiaTypes(1);
        }

        Wrapper<ShuiguoEntity> queryWrapper = new EntityWrapper<ShuiguoEntity>()
            .eq("shangjia_id", shuiguo.getShangjiaId())
            .eq("shuiguo_name", shuiguo.getShuiguoName())
            .eq("shuiguo_uuid_number", shuiguo.getShuiguoUuidNumber())
            .eq("shuiguo_types", shuiguo.getShuiguoTypes())
            .eq("shuiguo_erji_types", shuiguo.getShuiguoErjiTypes())
            .eq("tuijian_types", shuiguo.getTuijianTypes())
            .eq("tejia_types", shuiguo.getTejiaTypes())
            .eq("jinkou_types", shuiguo.getJinkouTypes())
            .eq("shuiguo_kucun_number", shuiguo.getShuiguoKucunNumber())
            .eq("shuiguo_clicknum", shuiguo.getShuiguoClicknum())
            .eq("shangxia_types", shuiguo.getShangxiaTypes())
            .eq("shuiguo_delete", shuiguo.getShuiguoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShuiguoEntity shuiguoEntity = shuiguoService.selectOne(queryWrapper);
        if(shuiguoEntity==null){
            shuiguo.setShuiguoClicknum(1);
//            shuiguo.setShangxiaTypes(1);
            shuiguo.setShuiguoDelete(1);
            shuiguo.setCreateTime(new Date());
            shuiguoService.insert(shuiguo);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShuiguoEntity shuiguo, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shuiguo:{}",this.getClass().getName(),shuiguo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("商家".equals(role))
//            shuiguo.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShuiguoEntity> queryWrapper = new EntityWrapper<ShuiguoEntity>()
            .notIn("id",shuiguo.getId())
            .andNew()
            .eq("shangjia_id", shuiguo.getShangjiaId())
            .eq("shuiguo_name", shuiguo.getShuiguoName())
            .eq("shuiguo_uuid_number", shuiguo.getShuiguoUuidNumber())
            .eq("shuiguo_types", shuiguo.getShuiguoTypes())
            .eq("shuiguo_erji_types", shuiguo.getShuiguoErjiTypes())
            .eq("tuijian_types", shuiguo.getTuijianTypes())
            .eq("tejia_types", shuiguo.getTejiaTypes())
            .eq("jinkou_types", shuiguo.getJinkouTypes())
            .eq("shuiguo_kucun_number", shuiguo.getShuiguoKucunNumber())
            .eq("shuiguo_clicknum", shuiguo.getShuiguoClicknum())
            .eq("shangxia_types", shuiguo.getShangxiaTypes())
            .eq("shuiguo_delete", shuiguo.getShuiguoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShuiguoEntity shuiguoEntity = shuiguoService.selectOne(queryWrapper);
        if("".equals(shuiguo.getShuiguoPhoto()) || "null".equals(shuiguo.getShuiguoPhoto())){
                shuiguo.setShuiguoPhoto(null);
        }
        if(shuiguoEntity==null){
            shuiguoService.updateById(shuiguo);//根据id更新
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
        ArrayList<ShuiguoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShuiguoEntity shuiguoEntity = new ShuiguoEntity();
            shuiguoEntity.setId(id);
            shuiguoEntity.setShuiguoDelete(2);
            list.add(shuiguoEntity);
        }
        if(list != null && list.size() >0){
            shuiguoService.updateBatchById(list);
        }
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
            List<ShuiguoEntity> shuiguoList = new ArrayList<>();//上传的东西
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
                            ShuiguoEntity shuiguoEntity = new ShuiguoEntity();
//                            shuiguoEntity.setShangjiaId(Integer.valueOf(data.get(0)));   //商家 要改的
//                            shuiguoEntity.setShuiguoName(data.get(0));                    //水果名称 要改的
//                            shuiguoEntity.setShuiguoUuidNumber(data.get(0));                    //水果编号 要改的
//                            shuiguoEntity.setShuiguoPhoto("");//详情和图片
//                            shuiguoEntity.setShuiguoTypes(Integer.valueOf(data.get(0)));   //一级分类 要改的
//                            shuiguoEntity.setShuiguoErjiTypes(Integer.valueOf(data.get(0)));   //二级分类 要改的
//                            shuiguoEntity.setTuijianTypes(Integer.valueOf(data.get(0)));   //是否推荐 要改的
//                            shuiguoEntity.setTejiaTypes(Integer.valueOf(data.get(0)));   //是否特价 要改的
//                            shuiguoEntity.setJinkouTypes(Integer.valueOf(data.get(0)));   //是否进口 要改的
//                            shuiguoEntity.setShuiguoKucunNumber(Integer.valueOf(data.get(0)));   //水果库存 要改的
//                            shuiguoEntity.setShuiguoOldMoney(data.get(0));                    //水果原价 要改的
//                            shuiguoEntity.setShuiguoNewMoney(data.get(0));                    //现价 要改的
//                            shuiguoEntity.setShuiguoClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            shuiguoEntity.setShuiguoContent("");//详情和图片
//                            shuiguoEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            shuiguoEntity.setShuiguoDelete(1);//逻辑删除字段
//                            shuiguoEntity.setCreateTime(date);//时间
                            shuiguoList.add(shuiguoEntity);


                            //把要查询是否重复的字段放入map中
                                //水果编号
                                if(seachFields.containsKey("shuiguoUuidNumber")){
                                    List<String> shuiguoUuidNumber = seachFields.get("shuiguoUuidNumber");
                                    shuiguoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shuiguoUuidNumber = new ArrayList<>();
                                    shuiguoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shuiguoUuidNumber",shuiguoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //水果编号
                        List<ShuiguoEntity> shuiguoEntities_shuiguoUuidNumber = shuiguoService.selectList(new EntityWrapper<ShuiguoEntity>().in("shuiguo_uuid_number", seachFields.get("shuiguoUuidNumber")).eq("shuiguo_delete", 1));
                        if(shuiguoEntities_shuiguoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShuiguoEntity s:shuiguoEntities_shuiguoUuidNumber){
                                repeatFields.add(s.getShuiguoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [水果编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shuiguoService.insertBatch(shuiguoList);
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
        PageUtils page = shuiguoService.queryPage(params);

        //字典表数据转换
        List<ShuiguoView> list =(List<ShuiguoView>)page.getList();
        for(ShuiguoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShuiguoEntity shuiguo = shuiguoService.selectById(id);
            if(shuiguo !=null){

                //点击数量加1
                shuiguo.setShuiguoClicknum(shuiguo.getShuiguoClicknum()+1);
                shuiguoService.updateById(shuiguo);

                //entity转view
                ShuiguoView view = new ShuiguoView();
                BeanUtils.copyProperties( shuiguo , view );//把实体数据重构到view中

                //级联表
                    ShangjiaEntity shangjia = shangjiaService.selectById(shuiguo.getShangjiaId());
                if(shangjia != null){
                    BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangjiaId(shangjia.getId());
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
    public R add(@RequestBody ShuiguoEntity shuiguo, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shuiguo:{}",this.getClass().getName(),shuiguo.toString());
        Wrapper<ShuiguoEntity> queryWrapper = new EntityWrapper<ShuiguoEntity>()
            .eq("shangjia_id", shuiguo.getShangjiaId())
            .eq("shuiguo_name", shuiguo.getShuiguoName())
            .eq("shuiguo_uuid_number", shuiguo.getShuiguoUuidNumber())
            .eq("shuiguo_types", shuiguo.getShuiguoTypes())
            .eq("shuiguo_erji_types", shuiguo.getShuiguoErjiTypes())
            .eq("tuijian_types", shuiguo.getTuijianTypes())
            .eq("tejia_types", shuiguo.getTejiaTypes())
            .eq("jinkou_types", shuiguo.getJinkouTypes())
            .eq("shuiguo_kucun_number", shuiguo.getShuiguoKucunNumber())
            .eq("shuiguo_clicknum", shuiguo.getShuiguoClicknum())
            .eq("shangxia_types", shuiguo.getShangxiaTypes())
            .eq("shuiguo_delete", shuiguo.getShuiguoDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShuiguoEntity shuiguoEntity = shuiguoService.selectOne(queryWrapper);
        if(shuiguoEntity==null){
            shuiguo.setShuiguoDelete(1);
            shuiguo.setCreateTime(new Date());
        shuiguoService.insert(shuiguo);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
