package jtwirc.types.hostTarget;

import jtwirc.enums.HOSTTARGET_MODE;

class HostTargetImpl implements HostTargetEvent
{

    private final HOSTTARGET_MODE mode;
    private final String target;
    private final int viwerAmount;
    private final String rawLine;

    HostTargetImpl(DefaultHostTargetBuilder builder)
    {
        this.mode = builder.mode;
        this.target = builder.target;
        this.viwerAmount = builder.viwerAmount;
        this.rawLine = builder.rawLine;
    }

    @Override
    public HOSTTARGET_MODE getMode()
    {
        return mode;
    }

    @Override
    public String getTarget()
    {
        return target;
    }

    @Override
    public int getViewerCount()
    {
        return viwerAmount;
    }

    @Override
    public String getRaw()
    {
        return rawLine;
    }
}
