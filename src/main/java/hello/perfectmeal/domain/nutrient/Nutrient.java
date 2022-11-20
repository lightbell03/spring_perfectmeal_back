package hello.perfectmeal.domain.nutrient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Nutrient {

    //식품 중량
    @Column(name = "food_Weight")
    @ColumnDefault("0")
    public double food_Weight;
    //에너지(kcal)
    @Column(name = "energy_Qy")
    @ColumnDefault("0")
    public double energy_Qy;
    //수분(%)
    @Column(name = "water_Qy")
    @ColumnDefault("0")
    public double water_Qy;
    //단백질(g)
    @Column(name = "prot_Qy")
    @ColumnDefault("0")
    public double prot_Qy;
    //지질(g)
    @Column(name = "ntrfs_Qy")
    @ColumnDefault("0")
    public double ntrfs_Qy;
    //회분(g)
    @Column(name = "ashs_Qy")
    @ColumnDefault("0")
    public double ashs_Qy;
    //탄수화물(g)
    @Column(name = "carbohydrate_Qy")
    @ColumnDefault("0")
    public double carbohydrate_Qy;
    //총 당류(g)
    @Column(name = "sugar_Qy")
    @ColumnDefault("0")
    public double sugar_Qy;
    //총 식이섬유(g)
    @Column(name = "fibtg_Qy")
    @ColumnDefault("0")
    public double fibtg_Qy;
    //총 아미노산(mg)
    @Column(name = "aat19_Qy")
    @ColumnDefault("0")
    public double aat19_Qy;
    //필수 아미노산(mg)
    @Column(name = "aae10a_Qy")
    @ColumnDefault("0")
    public double aae10a_Qy;
    //비필수 아미노산(mg)
    @Column(name = "aane_Qy")
    @ColumnDefault("0")
    public double aane_Qy;
    //총 지방산(g)
    @Column(name = "fafref_Qy")
    @ColumnDefault("0")
    public double fafref_Qy;
    //총 필수 지방산(g)
    @Column(name = "faessf_Qy")
    @ColumnDefault("0")
    public double faessf_Qy;
    //총 포화 지방산(g)
    @Column(name = "fasatf_Qy")
    @ColumnDefault("0")
    public double fasatf_Qy;
    //총 단일 불포화 지방산(g)
    @Column(name = "famsf_Qy")
    @ColumnDefault("0")
    public double famsf_Qy;
    //총 다중 불포화 지방산(g)
    @Column(name = "fapuf_Qy")
    @ColumnDefault("0")
    public double fapuf_Qy;
    //칼슘(mg)
    @Column(name = "clci_Qy")
    @ColumnDefault("0")
    public double clci_Qy;
    //철(mg)
    @Column(name = "irn_Qy")
    @ColumnDefault("0")
    public double irn_Qy;
    //마그네슘(mg)
    @Column(name = "mg_Qy")
    @ColumnDefault("0")
    public double mg_Qy;
    //인(mg)
    @Column(name = "phph_Qy")
    @ColumnDefault("0")
    public double phph_Qy;
    //칼륨(mg)
    @Column(name = "ptss_Qy")
    @ColumnDefault("0")
    public double ptss_Qy;
    //나트륨(mg)
    @Column(name = "na_Qy")
    @ColumnDefault("0")
    public double na_Qy;
    //아연(mg)
    @Column(name = "zn_Qy")
    @ColumnDefault("0")
    public double zn_Qy;
    //구리(mg)
    @Column(name = "cu_Qy")
    @ColumnDefault("0")
    public double cu_Qy;
    //망간(mg)
    @Column(name = "mn_Qy")
    @ColumnDefault("0")
    public double mn_Qy;
    //셀레늄(yg)
    @Column(name = "se_Qy")
    @ColumnDefault("0")
    public double se_Qy;
    //몰리브덴(yg)
    @Column(name = "mo_Qy")
    @ColumnDefault("0")
    public double mo_Qy;
    //요오드(yg)
    @Column(name = "id_Qy")
    @ColumnDefault("0")
    public double id_Qy;
    //레티놀(yg)
    @Column(name = "rtnl_Qy")
    @ColumnDefault("0")
    public double rtnl_Qy;
    //베타카로틴(yg)
    @Column(name = "catn_Qy")
    @ColumnDefault("0")
    public double catn_Qy;
    //비타민 D(D2+D3)(yg)
    @Column(name = "vitd_Qy")
    @ColumnDefault("0")
    public double vitd_Qy;
    //비타민 E(yg)
    @Column(name = "vite_Qy")
    @ColumnDefault("0")
    public double vite_Qy;
    //바타민 K1(yg)
    @Column(name = "vitk1_Qy")
    @ColumnDefault("0")
    public double vitk1_Qy;
    //비타민 B1(mg)
    @Column(name = "vtmn_B1_Qy")
    @ColumnDefault("0")
    public double vtmn_B1_Qy;
    //비타민 B2(mg)
    @Column(name = "vtmn_B2_Qy")
    @ColumnDefault("0")
    public double vtmn_B2_Qy;
    //니아신(mg)(mg)
    @Column(name = "nacn_Qy")
    @ColumnDefault("0")
    public double nacn_Qy;
    //판토텐산(비타민 B5)(mg)
    @Column(name = "pantac_Qy")
    @ColumnDefault("0")
    public double pantac_Qy;
    //비타민 B6 (mg)
    @Column(name = "pyrxn_Qy")
    @ColumnDefault("0")
    public double pyrxn_Qy;
    //비오틴(mg)
    @Column(name = "biot_Qy")
    @ColumnDefault("0")
    public double biot_Qy;
    //엽산(yg)
    @Column(name = "fol_Qy")
    @ColumnDefault("0")
    public double fol_Qy;
    //비타민 B12(yg)
    @Column(name = "vitb12_Qy")
    @ColumnDefault("0")
    public double vitb12_Qy;
    //비타민 C (mg)
    @Column(name = "vtmn_C_Qy")
    @ColumnDefault("0")
    public double vtmn_C_Qy;
    //콜레스테롤(mg)
    @Column(name = "chole_Qy")
    @ColumnDefault("0")
    public double chole_Qy;
    //식염상당량(g)
    @Column(name = "nacl_Qy")
    @ColumnDefault("0")
    public double nacl_Qy;
    //폐기율(%)
    @Column(name = "ref_Qy")
    @ColumnDefault("0")
    public double ref_Qy;
}
