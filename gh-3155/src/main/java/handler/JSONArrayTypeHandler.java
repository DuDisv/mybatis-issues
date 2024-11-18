package handler;

import io.micrometer.common.util.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Assert;
import test.JsonUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author dushuyu
 * @date 2024/11/18 16:27
 * @description
 */
@MappedTypes(value = { List.class })
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class JSONArrayTypeHandler extends BaseTypeHandler<List<Object>> {
    protected final Log log = LogFactory.getLog(this.getClass());

    protected final Class<?> type;

    /**
     * @since 3.5.6
     */
    protected Type genericType;

    /**
     * 默认初始化
     *
     * @param type 类型
     */
    public JSONArrayTypeHandler(Class<?> type) {
        this.type = type;
        if (log.isTraceEnabled()) {
            log.trace(this.getClass().getSimpleName() + "(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");
    }

    /**
     * 通过字段初始化
     *
     * @param type  类型
     * @param field 字段
     * @since 3.5.6
     */
    public JSONArrayTypeHandler(Class<?> type, Field field) {
        this(type);
        this.genericType = field.getGenericType();
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Object> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public List<Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        final String json = rs.getString(columnName);
        return StringUtils.isBlank(json) ? null : parse(json);
    }

    @Override
    public List<Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        final String json = rs.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : parse(json);
    }

    @Override
    public List<Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        final String json = cs.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : parse(json);
    }

    public Type getFieldType() {
        return this.genericType != null ? this.genericType : this.type;
    }

    public List<Object> parse(String json) {
        try{
            return JsonUtil.fromJsonString(json, List.class);
        }catch (Exception e){
            throw new RuntimeException("JSONArrayTypeHandler parse error");
        }
    }

    /**
     * 序列化json
     *
     * @param obj 对象信息
     * @return json字符串
     */
    public String toJson(List<Object> obj) {
        try{
            return JsonUtil.toJsonString(obj);
        }catch (Exception e){
            throw new RuntimeException("JSONArrayTypeHandler toJson error");
        }
    }

}
