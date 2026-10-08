package backend.service.impl;

import backend.repository.ICanBoRepository;
import backend.repository.impl.CanBoRepositoryImpl;
import backend.service.ICanBoService;
import entity.CanBo;

import java.util.List;

public class CanBoServiceImpl implements ICanBoService {
    private ICanBoRepository repository;

    public CanBoServiceImpl() {
        repository = new CanBoRepositoryImpl();
    }

    @Override
    public List<CanBo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CanBo> findByName(String ten) {
        return repository.findByName(ten);
    }

    @Override
    public boolean deleteByName(String ten) {
        return repository.deleteByName(ten);
    }

    @Override
    public boolean updateByName(String ten, String diaChi) {
        return repository.updateByName(ten, diaChi);
    }

    @Override
    public boolean save(CanBo canBo) {
        return repository.save(canBo);
    }

    @Override
    public boolean existByName(String hoTen) {
        return repository.existByName(hoTen);
    }
}