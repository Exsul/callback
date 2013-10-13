package net.exsul.callback;

public class Switch<T> {
    private Long last_change_time, last_state_duration;
    private T value, prev;
    private boolean inited;

    public Switch() {
        inited = false;
    }

    public boolean Update( T new_state ) {
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

    public T Current() {
        assert(IsInited());
        return value;
    }

    public T Previos() {
        assert(!IsFirstState());
        return prev;
    }

    public boolean ChangedTo( T prev, T cur ) {
        if (!IsInited())
            return false;
        if (IsFirstState())
            return false;
        return Equal(Previos(), prev) && Equal(Current(), cur);
    }

    public boolean Equal( final T a, final T b ) {
        return a == b;
    }

    private void UpdateState( T new_state ) {
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

    private void Init( T new_state ) {
        assert(!IsInited());
        inited = true;
        last_state_duration = last_change_time = GetCurrentTime();
        value = new_state;
    }

    private Long GetCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }
}

