package ru.chernysh.cookbook.database;

import android.arch.persistence.room.*;

@Entity(tableName = "material")
public class Material {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getIgnitionTemp() {
        return ignitionTemp;
    }

    public void setIgnitionTemp(double ignitionTemp) {
        this.ignitionTemp = ignitionTemp;
    }

    public double getMeltingTemp() {
        return meltingTemp;
    }

    public void setMeltingTemp(double meltingTemp) {
        this.meltingTemp = meltingTemp;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name_en")
    private String nameEn;

    @ColumnInfo(name = "name_ru")
    private String nameRu;

    @ColumnInfo(name = "density")
    private double density;

    @ColumnInfo(name = "ignition_temp")
    private double ignitionTemp;

    @ColumnInfo(name = "melting_temp")
    private double meltingTemp;

}
