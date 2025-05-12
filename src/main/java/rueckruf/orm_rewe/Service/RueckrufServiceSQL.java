package rueckruf.orm_rewe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.SQLSELECT.DynamischeFilterung;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repository.ProductRepository;
import rueckruf.orm_rewe.repository.RueckrufProductRepository;
import rueckruf.orm_rewe.repository.RueckrufRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RueckrufServiceSQL {
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RueckrufServiceSQL(RueckrufRepository rueckrufRepository, ProductRepository productRepository, JdbcTemplate jdbcTemplate) {
        this.rueckrufRepository = rueckrufRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }

    public List<Rueckruf> findAll() {
        return rueckrufRepository.findAll();
    }

    public void updateRueckruf(String value, Long id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Rueckruf.class.getMethod(value.split(":")[0], String.class);
        method.invoke(rueckrufRepository.findById(id).get(), value.split(":")[1]);
    }

    public void createRueckruf(Rueckruf rueckruf) {
        rueckrufRepository.save(rueckruf);
    }

    public Rueckruf findById(Long id) {
        return rueckrufRepository.findById(id).get();
    }

    public List<Rueckruf> findByAnything(String query) {


        String sql = new DynamischeFilterung(query).getQuery();
        List<Rueckruf> results = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Rueckruf.class)
        );
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        SqlRowSetMetaData meta = rs.getMetaData();

        return results.isEmpty() ? null : results;

    }

    public List<RueckrufWithProduct> findByAnything2(String query) {
        String sql = new DynamischeFilterung(query).getQuery();

        // Use a custom RowMapper to properly handle relationships
        List<RueckrufWithProduct> results = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    RueckrufWithProduct rwp = new RueckrufWithProduct();
                    rwp.setRueckruf(rueckrufRepository.findById(rs.getLong("rueckruf_id")).get());

                    List<Product> products = new ArrayList<>();

                    for(RueckrufProduct rp : rwp.getRueckruf().getProducts()) {
                        products.add(rp.getProduct());
                    }

                    rwp.setProductList(products);

                    return rwp;
                }
        );

        Set<RueckrufWithProduct> einzigartig = new LinkedHashSet<>(results);

        System.out.println(einzigartig.size());

        return new ArrayList<>(einzigartig);
    }
}