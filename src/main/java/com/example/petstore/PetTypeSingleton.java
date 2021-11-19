package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetTypeSingleton {
    private static PetTypeSingleton petInstance;
    public List<PetType> petTypeList = new ArrayList<PetType>();

    private PetTypeSingleton(){
        PetType type1 = new PetType();
        type1.setTypeId(1);
        type1.setTypeName("Dog");

        PetType type2 = new PetType();
        type2.setTypeId(2);
        type2.setTypeName("Cat");

        PetType type3 = new PetType();
        type3.setTypeId(3);
        type3.setTypeName("Bird");

        petTypeList.add(type1);
        petTypeList.add(type2);
        petTypeList.add(type3);
    }
    public static PetTypeSingleton getInstance()
    {
        if(petInstance == null)
            petInstance = new PetTypeSingleton();
        return petInstance;
    }
    public List<PetType> createNewPetType(PetType pettype)
    {
        petTypeList.add(pettype);
        return  petTypeList;
    }
    public List<PetType> getPetTypeList()
    {
        return petTypeList;
    }

    public PetType getPetTypeById(int id)
    {
        for (PetType type:petTypeList)
        {
            if(type.getTypeId() == id)
            {
                return type;
            }
        }
        return null;
    }

    public List<PetType> updatePetType(PetType pettype)
    {
        for (PetType type:petTypeList)
        {
            if(type.getTypeId() == pettype.getTypeId())
            {
                type.setTypeName(pettype.getTypeName());
                return petTypeList;
            }
        }
        return null;
    }
    public List<PetType> deletePetType(int id)
    {
        for (PetType type:petTypeList)
        {
            if(type.getTypeId() == id)
            {
                petTypeList.remove(type);
                return petTypeList;
            }
        }
        return null;
    }


}
