package ru.chernysh.cookbook.database;

import android.arch.persistence.room.*;

@Entity(tableName = "material")
public class Material {

    @PrimaryKey
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @ColumnInfo(name = "name_en")
    private String nameEn;

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @ColumnInfo(name = "name_ru")
    private String nameRu;

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @ColumnInfo(name = "density")
    private double density;

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    @ColumnInfo(name = "ignition_temp")
    private double ignitionTemp;

    public double getIgnitionTemp() {
        return ignitionTemp;
    }

    public void setIgnitionTemp(double ignitionTemp) {
        this.ignitionTemp = ignitionTemp;
    }

    @ColumnInfo(name = "melting_temp")
    private double meltingTemp;

    public double getMeltingTemp() {
        return meltingTemp;
    }

    public void setMeltingTemp(double meltingTemp) {
        this.meltingTemp = meltingTemp;
    }

}
