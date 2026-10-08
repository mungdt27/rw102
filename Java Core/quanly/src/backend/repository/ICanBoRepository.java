package backend.repository;

import entity.CanBo;

import java.util.List;

public interface ICanBoRepository {
    List<CanBo> findAll();

    List<CanBo> findByName(String ten);

    boolean deleteByName(String ten);

    boolean updateByName(String ten, String diaChi);

    boolean save(CanBo canBo);

    boolean existByName(String hoTen);
}