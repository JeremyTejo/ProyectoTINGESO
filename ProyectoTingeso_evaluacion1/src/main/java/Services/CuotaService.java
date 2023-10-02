package Services;

import Entities.Cuota;
import Repositories.CuotaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotaService {
    @Autowired
    private CuotaRepositories cuotaRepositories;

    public Cuota findById(Long id){
        return cuotaRepositories.findById(id).orElse(null);
    }
    public List<Cuota> findAll(){
        return cuotaRepositories.findAll();
    }
    public Cuota saveCuota(Cuota cuota){
        return cuotaRepositories.save(cuota);

    }
    public void deleteById(Long id){
        cuotaRepositories.deleteById(id);
    }
    public Cuota update(Long id, Cuota cuota){
        if (cuotaRepositories.existsById(id)){
            cuota.setIdCuota(id);
            return cuotaRepositories.save(cuota);
        }
        return null;
    }

}
