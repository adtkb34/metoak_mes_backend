package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Data
@ApiModel(value = "Calibresult对象", description = "")
public class Calibresult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long moProcessStepProductionResultId;

    @TableField("CameraSN")
    @FieldCode(no = "001", type = "val", label = "相机序列号")
    private String cameraSN;

    @TableField("TimeStamp")
    @FieldCode(no = "002", type = "val", label = "时间戳")
    private String timeStamp;

    @TableField("LeftSharpness")
    @FieldCode(no = "003", type = "val", position = 1, label = "左图清晰度")
    private Double leftSharpness;

    @TableField("RightSharpness")
    @FieldCode(no = "004", type = "val", position = 2, label = "右图清晰度")
    private Double rightSharpness;

    @TableField("Simor_validPattern")
    @FieldCode(no = "005", type = "val", label = "Simor 图案有效性")
    private Double simorValidPattern;

    @TableField("Simor_mean_reprojection_error")
    @FieldCode(no = "006", type = "val", label = "Simor 平均重投影误差")
    private Double simorMeanReprojectionError;

    @TableField("Simor_mean_left_reprojection_error")
    @FieldCode(no = "007", type = "val", position = 1, label = "Simor 左图平均误差")
    private Double simorMeanLeftReprojectionError;

    @TableField("Simor_mean_right_reprojection_error")
    @FieldCode(no = "008", type = "val", position = 2, label = "Simor 右图平均误差")
    private Double simorMeanRightReprojectionError;

    @TableField("Simor_max_left_reprojection_error")
    @FieldCode(no = "009", type = "val", position = 1, label = "Simor 最大左误差")
    private Double simorMaxLeftReprojectionError;

    @TableField("Simor_max_right_reprojection_error")
    @FieldCode(no = "010", type = "val", position = 2, label = "Simor 最大右误差")
    private Double simorMaxRightReprojectionError;

    @TableField("Simor_amplify_ratio")
    @FieldCode(no = "011", type = "val", label = "Simor 放大倍率")
    private Double simorAmplifyRatio;

    @TableField("Simor_left_trim")
    @FieldCode(no = "012", type = "val", position = 1, label = "Simor 左裁剪宽度")
    private Double simorLeftTrim;

    @TableField("Simor_right_trim")
    @FieldCode(no = "013", type = "val", position = 2, label = "Simor 右裁剪宽度")
    private Double simorRightTrim;

    @TableField("Simor_fov_h")
    @FieldCode(no = "014", type = "val", label = "Simor 水平视角")
    private Double simorFovH;

    @TableField("Simor_fov_v")
    @FieldCode(no = "015", type = "val", label = "Simor 垂直视角")
    private Double simorFovV;

    @TableField("Simor_simor_calc_result")
    @FieldCode(no = "016", type = "val", label = "Simor 计算结果")
    private Double simorSimorCalcResult;

    @TableField("ISP_validPattern")
    @FieldCode(no = "017", type = "val", label = "ISP 图案有效性")
    private Double ispValidPattern;

    @TableField("ISP_mean_reprojection_error")
    @FieldCode(no = "018", type = "val", label = "ISP 平均重投影误差")
    private Double ispMeanReprojectionError;

    @TableField("ISP_mean_left_reprojection_error")
    @FieldCode(no = "019", type = "val", position = 1, label = "ISP 左图平均误差")
    private Double ispMeanLeftReprojectionError;

    @TableField("ISP_mean_right_reprojection_error")
    @FieldCode(no = "020", type = "val", position = 2, label = "ISP 右图平均误差")
    private Double ispMeanRightReprojectionError;

    @TableField("ISP_max_left_reprojection_error")
    @FieldCode(no = "021", type = "val", position = 1, label = "ISP 最大左误差")
    private Double ispMaxLeftReprojectionError;

    @TableField("ISP_max_right_reprojection_error")
    @FieldCode(no = "022", type = "val", position = 2, label = "ISP 最大右误差")
    private Double ispMaxRightReprojectionError;

    @TableField("ISP_amplify_ratio")
    @FieldCode(no = "023", type = "val", label = "ISP 放大倍率")
    private Double ispAmplifyRatio;

    @TableField("ISP_left_trim")
    @FieldCode(no = "024", type = "val", position = 1, label = "ISP 左裁剪宽度")
    private Double ispLeftTrim;

    @TableField("ISP_right_trim")
    @FieldCode(no = "025", type = "val", position = 2, label = "ISP 右裁剪宽度")
    private Double ispRightTrim;

    @TableField("ISP_fov_h")
    @FieldCode(no = "026", type = "val", label = "ISP 水平视角")
    private Double ispFovH;

    @TableField("ISP_fov_v")
    @FieldCode(no = "027", type = "val", label = "ISP 垂直视角")
    private Double ispFovV;

    @TableField("ISP_simor_calc_result")
    @FieldCode(no = "028", type = "val", label = "ISP 计算结果")
    private Double ispSimorCalcResult;

    @TableField("ISP_RectifyVerticalOffset")
    @FieldCode(no = "029", type = "val", label = "ISP 垂直校正偏移")
    private Double ispRectifyVerticalOffset;

    @TableField("Simor_left_fx")
    @FieldCode(no = "030", type = "val", position = 1, label = "Simor 左焦距X")
    private Double simorLeftFx;

    @TableField("Simor_left_fy")
    @FieldCode(no = "031", type = "val", position = 1, label = "Simor 左焦距Y")
    private Double simorLeftFy;

    @TableField("Simor_left_cx")
    @FieldCode(no = "032", type = "val", position = 1, label = "Simor 左主点X")
    private Double simorLeftCx;

    @TableField("Simor_left_cy")
    @FieldCode(no = "033", type = "val", position = 1, label = "Simor 左主点Y")
    private Double simorLeftCy;

    @TableField("Simor_left_k1")
    @FieldCode(no = "034", type = "val", position = 1, label = "Simor 左径向畸变系数1")
    private Double simorLeftK1;

    @TableField("Simor_left_k2")
    @FieldCode(no = "035", type = "val", position = 1, label = "Simor 左径向畸变系数2")
    private Double simorLeftK2;

    @TableField("Simor_left_k3")
    @FieldCode(no = "036", type = "val", position = 1, label = "Simor 左径向畸变系数3")
    private Double simorLeftK3;

    @TableField("Simor_left_p1")
    @FieldCode(no = "037", type = "val", position = 1, label = "Simor 左切向畸变系数1")
    private Double simorLeftP1;

    @TableField("Simor_left_p2")
    @FieldCode(no = "038", type = "val", position = 1, label = "Simor 左切向畸变系数2")
    private Double simorLeftP2;

    @TableField("Simor_right_fx")
    @FieldCode(no = "039", type = "val", position = 2, label = "Simor 右焦距X")
    private Double simorRightFx;

    @TableField("Simor_right_fy")
    @FieldCode(no = "040", type = "val", position = 2, label = "Simor 右焦距Y")
    private Double simorRightFy;

    @TableField("Simor_right_cx")
    @FieldCode(no = "041", type = "val", position = 2, label = "Simor 右主点X")
    private Double simorRightCx;

    @TableField("Simor_right_cy")
    @FieldCode(no = "042", type = "val", position = 2, label = "Simor 右主点Y")
    private Double simorRightCy;

    @TableField("Simor_right_k1")
    @FieldCode(no = "043", type = "val", position = 2, label = "Simor 右径向畸变系数1")
    private Double simorRightK1;

    @TableField(exist = false)
    private Integer errorCode;

    @TableField("Simor_right_k2")
    @FieldCode(no = "044", type = "val", position = 2, label = "Simor 右径向畸变系数2")
    private Double simorRightK2;

    @TableField("Simor_right_k3")
    @FieldCode(no = "045", type = "val", position = 2, label = "Simor 右径向畸变系数3")
    private Double simorRightK3;

    @TableField("Simor_right_p1")
    @FieldCode(no = "046", type = "val", position = 2, label = "Simor 右切向畸变系数1")
    private Double simorRightP1;

    @TableField("Simor_right_p2")
    @FieldCode(no = "047", type = "val", position = 2, label = "Simor 右切向畸变系数2")
    private Double simorRightP2;

    @TableField("Simor_width")
    @FieldCode(no = "048", type = "val", label = "Simor 图像宽度")
    private Double simorWidth;

    @TableField("Simor_height")
    @FieldCode(no = "049", type = "val", label = "Simor 图像高度")
    private Double simorHeight;

    @TableField("Simor_bxf")
    @FieldCode(no = "050", type = "val", label = "Simor 基线X")
    private Double simorBxf;

    @TableField("Simor_baseline")
    @FieldCode(no = "051", type = "val", label = "Simor 基线")
    private Double simorBaseline;

    @TableField("Simor_focus")
    @FieldCode(no = "052", type = "val", label = "Simor 焦距")
    private Double simorFocus;

    @TableField("Simor_cx")
    @FieldCode(no = "053", type = "val", label = "Simor 主点X")
    private Double simorCx;

    @TableField("Simor_cy")
    @FieldCode(no = "054", type = "val", label = "Simor 主点Y")
    private Double simorCy;

    @TableField("Simor_out_width")
    @FieldCode(no = "055", type = "val", label = "Simor 输出宽度")
    private Double simorOutWidth;

    @TableField("simor_out_height")
    @FieldCode(no = "056", type = "val", label = "Simor 输出高度")
    private Double simorOutHeight;

    @TableField("simor_r0")
    @FieldCode(no = "057", type = "val", label = "Simor 旋转矩阵元素0")
    private Double simorR0;

    @TableField("simor_r1")
    @FieldCode(no = "058", type = "val", label = "Simor 旋转矩阵元素1")
    private Double simorR1;

    @TableField("simor_r2")
    @FieldCode(no = "059", type = "val", label = "Simor 旋转矩阵元素2")
    private Double simorR2;

    @TableField("simor_t0")
    @FieldCode(no = "060", type = "val", label = "Simor 平移向量元素0")
    private Double simorT0;

    @TableField("simor_t1")
    @FieldCode(no = "061", type = "val", label = "Simor 平移向量元素1")
    private Double simorT1;

    @TableField("simor_t2")
    @FieldCode(no = "062", type = "val", label = "Simor 平移向量元素2")
    private Double simorT2;

    @TableField("simor_left_r0")
    @FieldCode(no = "063", type = "val", position = 1, label = "Simor 左旋转矩阵元素0")
    private Double simorLeftR0;

    @TableField("simor_left_r1")
    @FieldCode(no = "064", type = "val", position = 1, label = "Simor 左旋转矩阵元素1")
    private Double simorLeftR1;

    @TableField("simor_left_r2")
    @FieldCode(no = "065", type = "val", position = 1, label = "Simor 左旋转矩阵元素2")
    private Double simorLeftR2;

    @TableField("simor_right_r0")
    @FieldCode(no = "066", type = "val", position = 2, label = "Simor 右旋转矩阵元素0")
    private Double simorRightR0;

    @TableField("simor_right_r1")
    @FieldCode(no = "067", type = "val", position = 2, label = "Simor 右旋转矩阵元素1")
    private Double simorRightR1;

    @TableField("simor_right_r2")
    @FieldCode(no = "068", type = "val", position = 2, label = "Simor 右旋转矩阵元素2")
    private Double simorRightR2;

    @TableField("simor_virtual_width")
    @FieldCode(no = "069", type = "val", label = "Simor 虚拟图像宽度")
    private Double simorVirtualWidth;

    @TableField("simor_virtual_height")
    @FieldCode(no = "070", type = "val", label = "Simor 虚拟图像高度")
    private Double simorVirtualHeight;

    @TableField("simor_roi_x")
    @FieldCode(no = "071", type = "val", label = "Simor 感兴趣区域X")
    private Double simorRoiX;

    @TableField("simor_roi_y")
    @FieldCode(no = "072", type = "val", label = "Simor 感兴趣区域Y")
    private Double simorRoiY;

    @TableField("isp_left_fx")
    @FieldCode(no = "073", type = "val", position = 1, label = "ISP 左焦距X")
    private Double ispLeftFx;

    @TableField("isp_left_fy")
    @FieldCode(no = "074", type = "val", position = 1, label = "ISP 左焦距Y")
    private Double ispLeftFy;

    @TableField("isp_left_cx")
    @FieldCode(no = "075", type = "val", position = 1, label = "ISP 左主点X")
    private Double ispLeftCx;

    @TableField("isp_left_cy")
    @FieldCode(no = "076", type = "val", position = 1, label = "ISP 左主点Y")
    private Double ispLeftCy;

    @TableField("isp_left_k1")
    @FieldCode(no = "077", type = "val", position = 1, label = "ISP 左径向畸变系数1")
    private Double ispLeftK1;

    @TableField("isp_left_k2")
    @FieldCode(no = "078", type = "val", position = 1, label = "ISP 左径向畸变系数2")
    private Double ispLeftK2;

    @TableField("isp_left_k3")
    @FieldCode(no = "079", type = "val", position = 1, label = "ISP 左径向畸变系数3")
    private Double ispLeftK3;

    @TableField("isp_left_k4")
    @FieldCode(no = "132", type = "val", position = 1, label = "ISP 左径向畸变系数4")
    private Double ispLeftK4;

    @TableField("isp_left_k5")
    @FieldCode(no = "133", type = "val", position = 1, label = "ISP 左径向畸变系数5")
    private Double ispLeftK5;

    @TableField("isp_left_k6")
    @FieldCode(no = "134", type = "val", position = 1, label = "ISP 左径向畸变系数6")
    private Double ispLeftK6;

    @TableField("isp_left_p1")
    @FieldCode(no = "080", type = "val", position = 1, label = "ISP 左切向畸变系数1")
    private Double ispLeftP1;

    @TableField("isp_left_p2")
    @FieldCode(no = "081", type = "val", position = 1, label = "ISP 左切向畸变系数2")
    private Double ispLeftP2;

    @TableField("isp_right_fx")
    @FieldCode(no = "082", type = "val", position = 2, label = "ISP 右焦距X")
    private Double ispRightFx;

    @TableField("isp_right_fy")
    @FieldCode(no = "083", type = "val", position = 2, label = "ISP 右焦距Y")
    private Double ispRightFy;

    @TableField("isp_right_cx")
    @FieldCode(no = "084", type = "val", position = 2, label = "ISP 右主点X")
    private Double ispRightCx;

    @TableField("isp_right_cy")
    @FieldCode(no = "085", type = "val", position = 2, label = "ISP 右主点Y")
    private Double ispRightCy;

    @TableField("isp_right_k1")
    @FieldCode(no = "086", type = "val", position = 2, label = "ISP 右径向畸变系数1")
    private Double ispRightK1;

    @TableField("isp_right_k2")
    @FieldCode(no = "087", type = "val", position = 2, label = "ISP 右径向畸变系数2")
    private Double ispRightK2;

    @TableField("isp_right_k3")
    @FieldCode(no = "088", type = "val", position = 2, label = "ISP 右径向畸变系数3")
    private Double ispRightK3;

    @TableField("isp_right_k4")
    @FieldCode(no = "135", type = "val", position = 2, label = "ISP 右径向畸变系数4")
    private Double ispRightK4;

    @TableField("isp_right_k5")
    @FieldCode(no = "136", type = "val", position = 2, label = "ISP 右径向畸变系数5")
    private Double ispRightK5;

    @TableField("isp_right_k6")
    @FieldCode(no = "137", type = "val", position = 2, label = "ISP 右径向畸变系数6")
    private Double ispRightK6;

    @TableField("isp_right_p1")
    @FieldCode(no = "089", type = "val", position = 2, label = "ISP 右切向畸变系数1")
    private Double ispRightP1;

    @TableField("isp_right_p2")
    @FieldCode(no = "090", type = "val", position = 2, label = "ISP 右切向畸变系数2")
    private Double ispRightP2;

    @TableField("isp_width")
    @FieldCode(no = "091", type = "val", label = "ISP 图像宽度")
    private Double ispWidth;

    @TableField("isp_height")
    @FieldCode(no = "092", type = "val", label = "ISP 图像高度")
    private Double ispHeight;

    @TableField("isp_bxf")
    @FieldCode(no = "093", type = "val", label = "ISP 基线X")
    private Double ispBxf;

    @TableField("isp_baseline")
    @FieldCode(no = "094", type = "val", label = "ISP 基线")
    private Double ispBaseline;

    @TableField("isp_focus")
    @FieldCode(no = "095", type = "val", label = "ISP 焦距")
    private Double ispFocus;

    @TableField("isp_cx")
    @FieldCode(no = "096", type = "val", label = "ISP 主点X")
    private Double ispCx;

    @TableField("isp_cy")
    @FieldCode(no = "097", type = "val", label = "ISP 主点Y")
    private Double ispCy;

    @TableField("isp_out_width")
    @FieldCode(no = "098", type = "val", label = "ISP 输出宽度")
    private Double ispOutWidth;

    @TableField("isp_out_height")
    @FieldCode(no = "099", type = "val", label = "ISP 输出高度")
    private Double ispOutHeight;

    @TableField("isp_r0")
    @FieldCode(no = "100", type = "val", label = "ISP 旋转矩阵元素0")
    private Double ispR0;

    @TableField("isp_r1")
    @FieldCode(no = "101", type = "val", label = "ISP 旋转矩阵元素1")
    private Double ispR1;

    @TableField("isp_r2")
    @FieldCode(no = "102", type = "val", label = "ISP 旋转矩阵元素2")
    private Double ispR2;

    @TableField("isp_t0")
    @FieldCode(no = "103", type = "val", label = "ISP 平移向量元素0")
    private Double ispT0;

    @TableField("isp_t1")
    @FieldCode(no = "104", type = "val", label = "ISP 平移向量元素1")
    private Double ispT1;

    @TableField("isp_t2")
    @FieldCode(no = "105", type = "val", label = "ISP 平移向量元素2")
    private Double ispT2;

    @TableField("isp_left_r0")
    @FieldCode(no = "106", type = "val", position = 1, label = "ISP 左旋转矩阵元素0")
    private Double ispLeftR0;

    @TableField("isp_left_r1")
    @FieldCode(no = "107", type = "val", position = 1, label = "ISP 左旋转矩阵元素1")
    private Double ispLeftR1;

    @TableField("isp_left_r2")
    @FieldCode(no = "108", type = "val", position = 1, label = "ISP 左旋转矩阵元素2")
    private Double ispLeftR2;

    @TableField("isp_right_r0")
    @FieldCode(no = "109", type = "val", position = 2, label = "ISP 右旋转矩阵元素0")
    private Double ispRightR0;

    @TableField("isp_right_r1")
    @FieldCode(no = "110", type = "val", position = 2, label = "ISP 右旋转矩阵元素1")
    private Double ispRightR1;

    @TableField("isp_right_r2")
    @FieldCode(no = "111", type = "val", position = 2, label = "ISP 右旋转矩阵元素2")
    private Double ispRightR2;

    @TableField("isp_virtual_width")
    @FieldCode(no = "112", type = "val", label = "ISP 虚拟图像宽度")
    private Double ispVirtualWidth;

    @TableField("isp_virtual_height")
    @FieldCode(no = "113", type = "val", label = "ISP 虚拟图像高度")
    private Double ispVirtualHeight;

    @TableField("isp_roi_x")
    @FieldCode(no = "114", type = "val", label = "ISP 感兴趣区域X")
    private Double ispRoiX;

    @TableField("isp_roi_y")
    @FieldCode(no = "115", type = "val", label = "ISP 感兴趣区域Y")
    private Double ispRoiY;

    @TableField("simor_ref_fx")
    @FieldCode(no = "116", type = "val", label = "Simor 参考焦距X")
    private Double simorRefFx;

    @TableField("simor_ref_fy")
    @FieldCode(no = "117", type = "val", label = "Simor 参考焦距Y")
    private Double simorRefFy;

    @TableField("simor_ref_cx")
    @FieldCode(no = "118", type = "val", label = "Simor 参考主点X")
    private Double simorRefCx;

    @TableField("simor_ref_cy")
    @FieldCode(no = "119", type = "val", label = "Simor 参考主点Y")
    private Double simorRefCy;

    @TableField("simor_ref_k1")
    @FieldCode(no = "120", type = "val", label = "Simor 参考径向畸变系数1")
    private Double simorRefK1;

    @TableField("simor_ref_k2")
    @FieldCode(no = "121", type = "val", label = "Simor 参考径向畸变系数2")
    private Double simorRefK2;

    @TableField("simor_ref_k3")
    @FieldCode(no = "122", type = "val", label = "Simor 参考径向畸变系数3")
    private Double simorRefK3;

    @TableField("simor_ref_p1")
    @FieldCode(no = "123", type = "val", label = "Simor 参考切向畸变系数1")
    private Double simorRefP1;

    @TableField("simor_ref_p2")
    @FieldCode(no = "124", type = "val", label = "Simor 参考切向畸变系数2")
    private Double simorRefP2;

    @TableField("simor_ref_width")
    @FieldCode(no = "125", type = "val", label = "Simor 参考图像宽度")
    private Double simorRefWidth;

    @TableField("simor_ref_height")
    @FieldCode(no = "126", type = "val", label = "Simor 参考图像高度")
    private Double simorRefHeight;

    @TableField("LeftCenterOffsetX")
    @FieldCode(no = "127", type = "val", position = 1, label = "左中心偏移X")
    private Integer leftCenterOffsetX;

    @TableField("LeftCenterOffsetY")
    @FieldCode(no = "128", type = "val", position = 1, label = "左中心偏移Y")
    private Integer leftCenterOffsetY;

    @TableField("RightCenterOffsetX")
    @FieldCode(no = "129", type = "val", position = 2, label = "右中心偏移X")
    private Integer rightCenterOffsetX;

    @TableField("RightCenterOffsetY")
    @FieldCode(no = "130", type = "val", position = 2, label = "右中心偏移Y")
    private Integer rightCenterOffsetY;
//
    @TableField("Station")
    @FieldCode(no = "131", type = "val", label = "站点")
    private Integer station;

}
