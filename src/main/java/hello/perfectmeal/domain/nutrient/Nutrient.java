package hello.perfectmeal.domain.nutrient;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Nutrient {

    //식품 중량
    public double food_Weight = 0;
    //에너지(kcal)
    public double energy_Qy = 0;
    //수분(%)
    public double water_Qy = 0;
    //단백(g)
    public double prot_Qy = 0;
    //지질(g)
    public double ntrfs_Qy = 0;
    //회분(g)
    public double ashs_Qy = 0;
    //탄수화물(g)
    public double carbohydrate_Qy = 0;
    //총 당류(g)
    public double sugar_Qy = 0;
    //총 식이섬유(g)
    public double fibtg_Qy = 0;
    //총 아미노산(mg)
    public double aat19_Qy = 0;
    //필수 아미노산(mg)
    public double aae10a_Qy = 0;
    //비필수 아미노산(mg)
    public double aane_Qy = 0;
    //총 지방산(g)
    public double fafref_Qy = 0;
    //총 필수 지방산(g)
    public double faessf_Qy = 0;
    //총 포화 지방산(g)
    public double fasatf_Qy = 0;
    //총 단일 불포화 지방산(g)
    public double famsf_Qy = 0;
    //총 다중 불포화 지방산(g)
    public double fapuf_Qy = 0;
    //칼슘(mg)
    public double clci_Qy = 0;
    //철(mg)
    public double irn_Qy = 0;
    //마그네슘(mg)
    public double mg_Qy = 0;
    //인(mg)
    public double phph_Qy = 0;
    //칼륨(mg)
    public double ptss_Qy = 0;
    //나트륨(mg)
    public double na_Qy = 0;
    //아연(mg)
    public double zn_Qy = 0;
    //구리(mg)
    public double cu_Qy = 0;
    //망간(mg)
    public double mn_Qy = 0;
    //셀레늄(yg)
    public double se_Qy = 0;
    //몰리브덴(yg)
    public double mo_Qy = 0;
    //요오드(yg)
    public double id_Qy = 0;
    //레티놀(yg)
    public double rtnl_Qy = 0;
    //베타카로틴(yg)
    public double catn_Qy = 0;
    //비타민 D(D2+D3)(yg)
    public double vitd_Qy = 0;
    //비타민 E(yg)
    public double vite_Qy = 0;
    //바타민 K1(yg)
    public double vitk1_Qy = 0;
    //비타민 B1(mg)
    public double vtmn_B1_Qy = 0;
    //비타민 B2(mg)
    public double vtmn_B2_Qy = 0;
    //니아신(mg)(mg)
    public double nacn_Qy = 0;
    //판토텐산(비타민 B5)(mg)
    public double pantac_Qy = 0;
    //비타민 B6 (mg)
    public double pyrxn_Qy = 0;
    //비오틴(mg)
    public double biot_Qy = 0;
    //엽산(yg)
    public double fol_Qy = 0;
    //비타민 B12(yg)
    public double vitb12_Qy = 0;
    //비타민 C (mg)
    public double vtmn_C_Qy = 0;
    //콜레스테롤(mg)
    public double chole_Qy = 0;
    //식염상당량(g)
    public double nacl_Qy = 0;
    //폐기율(%)
    public double ref_Qy = 0;
}
