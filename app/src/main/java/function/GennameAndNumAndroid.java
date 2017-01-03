package function;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenjunfan on 2017/1/3.
 */

public class GennameAndNumAndroid implements Parcelable {
    private String name;
    private float num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeFloat(this.num);
    }

    public GennameAndNumAndroid() {
    }

    protected GennameAndNumAndroid(Parcel in) {
        this.name = in.readString();
        this.num = in.readFloat();
    }

    public static final Parcelable.Creator<GennameAndNumAndroid> CREATOR = new Parcelable.Creator<GennameAndNumAndroid>() {
        @Override
        public GennameAndNumAndroid createFromParcel(Parcel source) {
            return new GennameAndNumAndroid(source);
        }

        @Override
        public GennameAndNumAndroid[] newArray(int size) {
            return new GennameAndNumAndroid[size];
        }
    };
}
