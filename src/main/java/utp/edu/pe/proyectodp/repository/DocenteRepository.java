package utp.edu.pe.proyectodp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.proyectodp.entity.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
