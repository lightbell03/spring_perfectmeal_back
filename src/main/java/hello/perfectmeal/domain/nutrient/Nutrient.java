package hello.perfectmeal.domain.nutrient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@NoArgsConstructor
public class Nutrient {

    //에너지(kcal)
    @Column(name = "energy_Qy", scale = 2)
    @ColumnDefault("0")
    public double energy_Qy = 0;
    //단백질(g)

    @Column(name = "prot_Qy", scale = 2)
    @ColumnDefault("0")
    public double prot_Qy = 0;
    //지질(g)
    @Column(name = "ntrfs_Qy", scale = 2)
    @ColumnDefault("0")
    public double ntrfs_Qy = 0;
    //탄수화물(g)
    @Column(name = "carbohydrate_Qy", scale = 2)
    @ColumnDefault("0")
    public double carbohydrate_Qy = 0;
    //총 당류(g)
    @Column(name = "sugar_Qy", scale = 2)
    @ColumnDefault("0")
    public double sugar_Qy = 0;
    //총 식이섬유(g)
    @Column(name = "fibtg_Qy", scale = 2)
    @ColumnDefault("0")
    public double fibtg_Qy = 0;
    //필수 아미노산(mg)
    @Column(name = "aae10a_Qy", scale = 2)
    @ColumnDefault("0")
    public double aae10a_Qy = 0;
    //총 필수 지방산(g)
    @Column(name = "faessf_Qy", scale = 2)
    @ColumnDefault("0")
    public double faessf_Qy = 0;
    //총 포화 지방산(g)
    @Column(name = "fasatf_Qy", scale = 2)
    @ColumnDefault("0")
    public double fasatf_Qy = 0;
    //칼슘(mg)
    @Column(name = "clci_Qy", scale = 2)
    @ColumnDefault("0")
    public double clci_Qy = 0;
    //철(mg)
    @Column(name = "irn_Qy", scale = 2)
    @ColumnDefault("0")
    public double irn_Qy = 0;
    //마그네슘(mg)
    @Column(name = "mg_Qy", scale = 2)
    @ColumnDefault("0")
    public double mg_Qy = 0;
    //인(mg)
    @Column(name = "phph_Qy", scale = 2)
    @ColumnDefault("0")
    public double phph_Qy = 0;
    //칼륨(mg)
    @Column(name = "ptss_Qy", scale = 2)
    @ColumnDefault("0")
    public double ptss_Qy = 0;
    //나트륨(mg)
    @Column(name = "na_Qy", scale = 2)
    @ColumnDefault("0")
    public double na_Qy = 0;
    //아연(mg)
    @Column(name = "zn_Qy", scale = 2)
    @ColumnDefault("0")
    public double zn_Qy = 0;
    //구리(mg)
    @Column(name = "cu_Qy", scale = 2)
    @ColumnDefault("0")
    public double cu_Qy = 0;
    //망간(mg)
    @Column(name = "mn_Qy", scale = 2)
    @ColumnDefault("0")
    public double mn_Qy = 0;
    //셀레늄(yg)
    @Column(name = "se_Qy", scale = 2)
    @ColumnDefault("0")
    public double se_Qy = 0;
    //몰리브덴(yg)
    @Column(name = "mo_Qy", scale = 2)
    @ColumnDefault("0")
    public double mo_Qy = 0;
    //요오드(yg)
    @Column(name = "id_Qy", scale = 2)
    @ColumnDefault("0")
    public double id_Qy = 0;
    //레티놀(yg)
    @Column(name = "rtnl_Qy", scale = 2)
    @ColumnDefault("0")
    public double rtnl_Qy = 0;
    //비타민 D(D2+D3)(yg)
    @Column(name = "vitd_Qy", scale = 2)
    @ColumnDefault("0")
    public double vitd_Qy = 0;
    //비타민 E(yg)
    @Column(name = "vite_Qy", scale = 2)
    @ColumnDefault("0")
    public double vite_Qy = 0;
    //바타민 K1(yg)
    @Column(name = "vitk1_Qy", scale = 2)
    @ColumnDefault("0")
    public double vitk1_Qy = 0;
    //비타민 B1(mg)
    @Column(name = "vtmn_B1_Qy", scale = 2)
    @ColumnDefault("0")
    public double vtmn_B1_Qy = 0;
    //비타민 B2(mg)
    @Column(name = "vtmn_B2_Qy", scale = 2)
    @ColumnDefault("0")
    public double vtmn_B2_Qy = 0;
    //니아신(mg)(mg)
    @Column(name = "nacn_Qy", scale = 2)
    @ColumnDefault("0")
    public double nacn_Qy = 0;
    //판토텐산(비타민 B5)(mg)
    @Column(name = "pantac_Qy", scale = 2)
    @ColumnDefault("0")
    public double pantac_Qy = 0;
    //비타민 B6 (mg)
    @Column(name = "pyrxn_Qy", scale = 2)
    @ColumnDefault("0")
    public double pyrxn_Qy = 0;
    //비오틴(mg)
    @Column(name = "biot_Qy", scale = 2)
    @ColumnDefault("0")
    public double biot_Qy = 0;
    //엽산(yg)
    @Column(name = "fol_Qy", scale = 2)
    @ColumnDefault("0")
    public double fol_Qy = 0;
    //비타민 B12(yg)
    @Column(name = "vitb12_Qy", scale = 2)
    @ColumnDefault("0")
    public double vitb12_Qy = 0;
    //비타민 C (mg)
    @Column(name = "vtmn_C_Qy", scale = 2)
    @ColumnDefault("0")
    public double vtmn_C_Qy = 0;
    //콜레스테롤(mg)
    @Column(name = "chole_Qy", scale = 2)
    @ColumnDefault("0")
    public double chole_Qy = 0;
}
