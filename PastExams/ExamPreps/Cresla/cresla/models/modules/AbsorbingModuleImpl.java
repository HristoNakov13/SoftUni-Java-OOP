package cresla.models.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbsorbingModuleImpl extends ModuleImpl implements AbsorbingModule {
    private int heatAbsorbing;

    protected AbsorbingModuleImpl(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }
}
