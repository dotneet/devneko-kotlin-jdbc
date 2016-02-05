package net.devneko.kjdbc

class UpdateParameterAndConditionBulder() : UpdateParameterBuilder() {

    private var _cond:String = "";
    public val condition:String get() = _cond

    fun where(cond:String, block:ParameterMapper.()->Unit):Unit {
        this._cond = cond;
        _setList.add(block)
    }
}
