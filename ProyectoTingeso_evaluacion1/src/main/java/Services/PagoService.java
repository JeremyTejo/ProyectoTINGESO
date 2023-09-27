package Services;

import Entities.Pago;
import Repositories.PagoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PagoService implements PagoRepositories {

    @Autowired
    private PagoRepositories pagoRepositories;



    public List<Pago> todosLosPagos(){
        return pagoRepositories.findAll();
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    public Pago guardarPago( Pago pago){
        return pagoRepositories.save(pago);
    }


    @Override
    public List<Pago> findAll() {
        return null;
    }

    @Override
    public List<Pago> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Pago> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Pago> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Pago entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pago> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Pago> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Pago> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Pago> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Pago> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Pago> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Pago> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Pago getOne(Long aLong) {
        return null;
    }

    @Override
    public Pago getById(Long aLong) {
        return null;
    }

    @Override
    public Pago getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Pago> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Pago> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Pago> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Pago> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Pago> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Pago> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Pago, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}

