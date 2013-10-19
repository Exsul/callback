package net.exsul.callback2;

public class Switch {
    private Long last_change_time, last_state_duration;
    private String value, prev;
    private boolean inited;

    public Switch() {
        inited = false;
    }

    public boolean Update( String new_state ) {
        if (IsInited())
            if (value == new_state)
                return false;
        UpdateState(new_state);
        return true;
    }

    public boolean IsFirstState() {
        assert(IsInited());
        return last_state_duration == last_change_time;
    }

    public Long PreviosDuration() {
        assert(!IsFirstState());
        return last_state_duration;
    }

    public Long CurrentDuration() {
        assert(IsInited());
        return GetCurrentTime() - last_state_duration;
    }

    public String Current() {
        assert(IsInited());
        return value;
    }

    public String Previos() {
        assert(!IsFirstState());
        return prev;
    }

    public boolean ChangedTo( String prev, String cur ) {
        if (!IsInited())
            return false;
        if (IsFirstState())
            return false;
        return Equal(Previos(), prev) && Equal(Current(), cur);
    }

    public boolean Equal( final String a, final String b ) {
        return a.equalsIgnoreCase(b);
    }

    private void UpdateState( String new_state ) {
        if (!IsInited()){
            Init(new_state);
            return;
        }
        Long cur = GetCurrentTime();
        last_state_duration = cur - last_change_time;
        last_change_time = cur;
        prev = value;
        value = new_state;
        assert(!IsFirstState());
    }

    public boolean IsInited() {
        return inited;
    }

    private void Init( String new_state ) {
        assert(!IsInited());
        inited = true;
        last_state_duration = last_change_time = GetCurrentTime();
        value = new_state;
    }

    private Long GetCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }
}

