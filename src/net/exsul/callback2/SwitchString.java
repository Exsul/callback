package net.exsul.callback2;

public class SwitchString extends Switch<String> {
    @Override
    public boolean Equal( final String a, final String b ) {
        return a.equalsIgnoreCase(b);
    }

}
