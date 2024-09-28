"use client"
import { Template, ImageCard } from "@/components/Index";
import { Image } from "@/resources/image/image.resources";
import { useImageService } from "@/resources/image/image.service";
import { useState } from "react";

export default function GalleryPages() {
    const [images, setImages] = useState<Image[]>([]);
    const userService = useImageService();

    async function searchImages() {
        const result = await userService.search();
        setImages(result);
        console.table(result);
    }

    return (
        <Template>
            <button className="bg-gray-500" onClick={searchImages} >SEARCH</button>
            <h4 className="font-bold text-3xl py-2">Gallery</h4>
            <section className="grid grid-cols-4 gap-8">
                <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />

            </section>
        </Template>
    );
}