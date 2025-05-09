package rueckruf.orm_rewe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.SQLSELECT.DynamischeFilterung;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.repository.ProductRepository;
import rueckruf.orm_rewe.repository.RueckrufProductRepository;
import rueckruf.orm_rewe.repository.RueckrufRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class RueckrufServiceSQL {
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final RueckrufProductRepository rueckrufProductRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RueckrufServiceSQL(RueckrufRepository rueckrufRepository,
                              ProductRepository productRepository, RueckrufProductRepository rueckrufProductRepository, JdbcTemplate jdbcTemplate) {
        this.rueckrufRepository = rueckrufRepository;
        this.productRepository = productRepository;
        this.rueckrufProductRepository = rueckrufProductRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rueckruf> findAll() {
        return rueckrufRepository.findAll();
    }

    public void updateRueckruf(String value, Long id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional rueckruf = rueckrufRepository.findById(id);
        Rueckruf r = null;
        if (rueckruf.isPresent()) {
            r = (Rueckruf) rueckruf.get();
            Method method = Rueckruf.class.getMethod(value, String.class);
            method.invoke(r, "xyz");
        }
        rueckrufRepository.save(r);
    }

    public List<Rueckruf> findByAnything(String query) {
        String sql = new DynamischeFilterung(query).getQuery();
        System.out.println(sql);
        List<Rueckruf> results = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Rueckruf.class)
        );

        return results.isEmpty() ? null : results;

    }
}