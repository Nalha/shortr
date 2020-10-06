package jesper.hasteen.shortr.controller;

public class LongUrlForm {

    private String longUrl;

    public LongUrlForm() {
    }

    public LongUrlForm(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String toString() {
        return "LongUrlForm{" +
                "longUrl='" + longUrl + '\'' +
                '}';
    }
}
