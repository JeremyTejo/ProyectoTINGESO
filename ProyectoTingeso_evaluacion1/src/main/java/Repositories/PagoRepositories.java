package Repositories;

import Entities.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepositories extends JpaRepository<Pago,Long> {


}
