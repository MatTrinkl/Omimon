package Omidex.Values;

public class Health extends BaseValue implements ValueUpdatable{

  public Health(int value) {
    super.setValue(value);
  }
  @Override
  public boolean updateValue(int value) {
    if(this.value-value<=0){
      this.value=0;
      return false;
    }
    value = BaseValue.CastValue(value);
    return true;
  }
  public void Heal(){
    super.setValue(255);
  }
}
