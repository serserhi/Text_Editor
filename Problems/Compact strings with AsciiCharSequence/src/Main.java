
import java.lang.CharSequence;

class AsciiCharSequence implements CharSequence/* extends/implements */ {
    // implementation
    private byte[] cadena;

    public AsciiCharSequence (byte[] cadena) {
        this.cadena = cadena.clone();
    }
    @Override
    public int  length () {
        return cadena.length;
    }
    @Override
    public char charAt(int id) {
        return (char) cadena[id];
    }
    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(java.util.Arrays.copyOfRange(cadena, start, end));
    }
    @Override
    public String toString() {
        return new String(cadena);
    }
}