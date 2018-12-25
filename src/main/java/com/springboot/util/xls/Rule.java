package com.springboot.util.xls;

import java.util.Date;


public interface Rule {
    public boolean validate(Object value);
}

abstract class BaseRule implements Rule {

    @Override
    public boolean validate(Object value) {
        if (null != value) {
            if (String.class.isInstance(value)) {
                String val = (String) value;
                return validate(val);
            } else if (Number.class.isInstance(value)) {
                double val = ((Number) value).doubleValue();
                return validate(val);
            } else if (Date.class.isInstance(value)) {
                Date val = (Date) value;
                return validate(val);
            } else {
                throw new ClassCastException("can handler " + value.getClass().getSimpleName());
            }
        } else {
            return false;
        }
    }

    protected abstract boolean validate(double value);

    protected abstract boolean validate(String value);

    protected abstract boolean validate(Date value);
}

class Gt extends BaseRule {

    private Comparable<?> value;

    public Gt(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Gt(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Gt(double value) {
        this.value = value;
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value > val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}

class Ge extends BaseRule {

    private Comparable<?> value;

    public Ge(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Ge(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Ge(double value) {
        this.value = value;
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value >= val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

class Lt extends BaseRule {

    private Comparable<?> value;

    public Lt(double value) {
        this.value = value;
    }

    public Lt(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Lt(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value < val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result == -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result == -1) {
            return true;
        } else {
            return false;
        }
    }
}

class Le extends BaseRule {

    private Comparable<?> value;

    public Le(double value) {
        this.value = value;
    }

    public Le(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Le(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value <= val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result <= 0) {
            return true;
        } else {
            return false;
        }
    }
}

class Eq extends BaseRule {

    private Comparable<?> value;

    public Eq(double value) {
        this.value = value;
    }

    public Eq(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Eq(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value == val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }
}

class Ne extends BaseRule {

    private Comparable<?> value;

    public Ne(double value) {
        this.value = value;
    }

    public Ne(String value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    public Ne(Date value) {
        if (null != value) {
            this.value = value;
        } else {
            throw new NullPointerException("value must be not null.");
        }
    }

    @Override
    protected boolean validate(double value) {
        double val = (Double) this.value;
        if (value != val) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(String value) {
        int result = value.compareTo((String) this.value);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean validate(Date value) {
        int result = value.compareTo((Date) this.value);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }
}