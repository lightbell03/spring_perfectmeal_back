package hello.perfectmeal.domain.nutrient;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Nutrient {

    //식품 중량
    private Double food_weight;
    //에너지(kcal)
    private Double energy_Qy;
    //수분(%)
    private Double water_Qy;
    //단백(g)
    private Double prot_Qy;
    //지질(g)
    private Double ntrfs_Qy;
    //회분(g)
    private Double ashs_Qy;
    //탄수화물(g)
    private Double carbohydrate_Qy;
    //총 당류(g)
    private Double sugar_Qy;
    //총 식이섬유(g)
    private Double fibtg_Qy;
    //총 아미노산(mg)
    private Double aat19_Qy;
    //필수 아미노산(mg)
    private Double aae10a_Qy;
    //비필수 아미노산(mg)
    private Double aane_Qy;
    //총 지방산(g)
    private Double fafref_Qy;
    //총 필수 지방산(g)
    private Double faessf_Qy;
    //총 포화 지방산(g)
    private Double fasatf_Qy;
    //총 단일 불포화 지방산(g)
    private Double famsf_Qy;
    //총 다중 불포화 지방산(g)
    private Double fapuf_Qy;
    //칼슘(mg)
    private Double clci_Qy;
    //철(mg)
    private Double irn_Qy;
    //마그네슘(mg)
    private Double mg_Qy;
    //인(mg)
    private Double phph_Qy;
    //칼륨(mg)
    private Double ptss_Qy;
    //나트륨(mg)
    private Double na_Qy;
    //아연(mg)
    private Double zn_Qy;
    //구리(mg)
    private Double cu_Qy;
    //망간(mg)
    private Double mn_Qy;
    //셀레늄(yg)
    private Double se_Qy;
    //몰리브덴(yg)
    private Double mo_Qy;
    //요오드(yg)
    private Double id_Qy;
    //레티놀(yg)
    private Double rtnl_Qy;
    //베타카로틴(yg)
    private Double catn_Qy;
    //비타민 D(D2+D3)(yg)
    private Double vitd_Qy;
    //비타민 E(yg)
    private Double vite_Qy;
    //바타민 K1(yg)
    private Double vitk1_Qy;
    //비타민 B1(mg)
    private Double vtmn_B1_Qy;
    //비타민 B2(mg)
    private Double vtmn_B2_Qy;
    //니아신(mg)(mg)
    private Double nacn_Qy;
    //판토텐산(비타민 B5)(mg)
    private Double pantac_Qy;
    //비타민 B6 (mg)
    private Double pyrxn_Qy;
    //비오틴(mg)
    private Double biot_Qy;
    //엽산(yg)
    private Double fol_Qy;
    //비타민 B12(yg)
    private Double vitb12_Qy;
    //비타민 C (mg)
    private Double vtmn_C_Qy;
    //콜레스테롤(mg)
    private Double chole_Qy;
    //식염상당량(g)
    private Double nacl_Qy;
    //폐기율(%)
    private Double ref_Qy;
}
