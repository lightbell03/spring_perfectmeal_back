package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;

public class UnderNutrient {

    public static Nutrient getManUnderNutrient(Account account, Nutrient todayNutrient){
        Nutrient nutrient = new Nutrient();
        nutrient.energy_Qy = 2600.0 - todayNutrient.energy_Qy;
        nutrient.prot_Qy = Math.round((account.getWeight() * 0.8 - todayNutrient.prot_Qy) * 100) / 100;
        nutrient.ntrfs_Qy = Math.round((todayNutrient.energy_Qy * 0.2 - todayNutrient.ntrfs_Qy) * 100) / 100;
        nutrient.carbohydrate_Qy = Math.round((todayNutrient.energy_Qy * 0.55 - todayNutrient.carbohydrate_Qy) * 100) / 100;
        nutrient.sugar_Qy = 36.0 - todayNutrient.sugar_Qy;
        nutrient.fibtg_Qy = 25 - todayNutrient.fibtg_Qy;
        nutrient.faessf_Qy = 2 - todayNutrient.faessf_Qy;
        nutrient.fasatf_Qy = 15 - todayNutrient.fasatf_Qy;
        nutrient.clci_Qy = 2000 - todayNutrient.clci_Qy;
        nutrient.irn_Qy = 10 - todayNutrient.irn_Qy;
        nutrient.mg_Qy = 350 - todayNutrient.mg_Qy;
        nutrient.phph_Qy = 700 - todayNutrient.phph_Qy;
        nutrient.ptss_Qy = 3500 - todayNutrient.ptss_Qy;
        nutrient.na_Qy = 2000 - todayNutrient.na_Qy;
        nutrient.zn_Qy = 10 - todayNutrient.zn_Qy;
        nutrient.cu_Qy = 0.8 - todayNutrient.cu_Qy;
        nutrient.mn_Qy = 4 - todayNutrient.mn_Qy;
        nutrient.se_Qy = 200 - todayNutrient.se_Qy;
        nutrient.mo_Qy = 200 - todayNutrient.mo_Qy;
        nutrient.id_Qy = 150 - todayNutrient.id_Qy;
        nutrient.rtnl_Qy = 750 - todayNutrient.rtnl_Qy;
        nutrient.vitd_Qy = 20 - todayNutrient.vitd_Qy;
        nutrient.vite_Qy = 10 -todayNutrient.vite_Qy;
        nutrient.vitk1_Qy = 120 - todayNutrient.vitk1_Qy;
        nutrient.vtmn_B1_Qy = 1.2 - todayNutrient.vtmn_B1_Qy;
        nutrient.vtmn_B2_Qy = 1.5 - todayNutrient.vtmn_B2_Qy;
        nutrient.nacn_Qy = 16 - todayNutrient.nacn_Qy;
        nutrient.pantac_Qy = 5 - todayNutrient.pantac_Qy;
        nutrient.pyrxn_Qy = 1.5 - todayNutrient.pyrxn_Qy;
        nutrient.biot_Qy = 0.03 - todayNutrient.biot_Qy;
        nutrient.fol_Qy = 400 - todayNutrient.fol_Qy;
        nutrient.vitb12_Qy = 2.4 - todayNutrient.vitb12_Qy;
        nutrient.vtmn_C_Qy = 100 - todayNutrient.vtmn_C_Qy;
        nutrient.chole_Qy = 250 - todayNutrient.chole_Qy;

        return nutrient;
    }

    public static Nutrient getWomanUnderNutrient(Account account, Nutrient todayNutrient){
        Nutrient nutrient = new Nutrient();
        nutrient.energy_Qy = 2000.0 - todayNutrient.energy_Qy;
        nutrient.prot_Qy = Math.round((account.getWeight() * 0.8 - todayNutrient.prot_Qy) * 100) / 100;
        nutrient.ntrfs_Qy = Math.round((todayNutrient.energy_Qy * 0.2 - todayNutrient.ntrfs_Qy) * 100) / 100;
        nutrient.carbohydrate_Qy = Math.round((todayNutrient.energy_Qy * 0.55 - todayNutrient.carbohydrate_Qy) * 100) / 100;
        nutrient.sugar_Qy = 36.0 - todayNutrient.sugar_Qy;
        nutrient.fibtg_Qy = 25 - todayNutrient.fibtg_Qy;
        nutrient.faessf_Qy = 2 - todayNutrient.faessf_Qy;
        nutrient.fasatf_Qy = 15 - todayNutrient.fasatf_Qy;
        nutrient.clci_Qy = 2000 - todayNutrient.clci_Qy;
        nutrient.irn_Qy = 14 - todayNutrient.irn_Qy;
        nutrient.mg_Qy = 280 - todayNutrient.mg_Qy;
        nutrient.phph_Qy = 700 - todayNutrient.phph_Qy;
        nutrient.ptss_Qy = 3500 - todayNutrient.ptss_Qy;
        nutrient.na_Qy = 2000 - todayNutrient.na_Qy;
        nutrient.zn_Qy = 8 - todayNutrient.zn_Qy;
        nutrient.cu_Qy = 0.8 - todayNutrient.cu_Qy;
        nutrient.mn_Qy = 4 - todayNutrient.mn_Qy;
        nutrient.se_Qy = 200 - todayNutrient.se_Qy;
        nutrient.mo_Qy = 200 - todayNutrient.mo_Qy;
        nutrient.id_Qy = 150 - todayNutrient.id_Qy;
        nutrient.rtnl_Qy = 650 - todayNutrient.rtnl_Qy;
        nutrient.vitd_Qy = 20 - todayNutrient.vitd_Qy;
        nutrient.vite_Qy = 10 -todayNutrient.vite_Qy;
        nutrient.vitk1_Qy = 90 - todayNutrient.vitk1_Qy;
        nutrient.vtmn_B1_Qy = 1.1 - todayNutrient.vtmn_B1_Qy;
        nutrient.vtmn_B2_Qy = 1.2 - todayNutrient.vtmn_B2_Qy;
        nutrient.nacn_Qy = 14 - todayNutrient.nacn_Qy;
        nutrient.pantac_Qy = 5 - todayNutrient.pantac_Qy;
        nutrient.pyrxn_Qy = 1.4 - todayNutrient.pyrxn_Qy;
        nutrient.biot_Qy = 0.03 - todayNutrient.biot_Qy;
        nutrient.fol_Qy = 400 - todayNutrient.fol_Qy;
        nutrient.vitb12_Qy = 2.4 - todayNutrient.vitb12_Qy;
        nutrient.vtmn_C_Qy = 100 - todayNutrient.vtmn_C_Qy;
        nutrient.chole_Qy = 250 - todayNutrient.chole_Qy;

        return nutrient;
    }
}
