package Services;

import Entities.Alumno;
import Repositories.AlumnoRepositories;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public  class AlumnoService implements AlumnoRepositories   {
    @Autowired
    private AlumnoRepositories alumnoRepositories;

    @Override
    public List<Alumno> findAll() {
        return alumnoRepositories.findAll();
    }

    @Override
    public List<Alumno> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Alumno> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Alumno> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long Id) {
        alumnoRepositories.deleteById(Id);
    }

    @Override
    public void delete(Alumno entity) {
        alumnoRepositories.delete(entity);

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Alumno> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Alumno> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Alumno> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Alumno> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Alumno> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Alumno> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Alumno> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Alumno getOne(Long aLong) {
        return null;
    }

    @Override
    public Alumno getById(Long aLong) {
        return null;
    }

    @Override
    public Alumno getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Alumno> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Alumno> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Alumno> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Alumno> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Alumno> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Alumno> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Alumno, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


}
