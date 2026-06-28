package utp.edu.pe.proyectodp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.proyectodp.model.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion,Long> {
}